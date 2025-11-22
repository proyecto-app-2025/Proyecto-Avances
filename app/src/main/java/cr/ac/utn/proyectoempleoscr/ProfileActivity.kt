package cr.ac.utn.proyectoempleoscr

import Controller.ProfileController
import Entity.Profile
import Util.Util
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.util.Calendar

class ProfileActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var TextID: TextInputEditText
    private lateinit var TextName: TextInputEditText
    private lateinit var TextFLastName: TextInputEditText
    private lateinit var TextSLastName: TextInputEditText
    private lateinit var TextEmail: TextInputEditText
    private lateinit var TextPhone: TextInputEditText
    private lateinit var TextBirthdate: TextInputEditText
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0
    private var IsEditMode: Boolean = false

    private lateinit var profileController: ProfileController
    private lateinit var menuitemDelete: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        profileController = ProfileController(this)

        TextID        = findViewById(R.id.TextID_profile)
        TextName      = findViewById(R.id.TextName_profile)
        TextFLastName = findViewById(R.id.TextFLastName_profile)
        TextSLastName = findViewById(R.id.TextSLastName_profile)
        TextEmail     = findViewById(R.id.TextEmail_profile)
        TextPhone     = findViewById(R.id.TextPhone_profile)
        TextBirthdate = findViewById(R.id.TextBirthdate_profile)

        ResetDate()

        val btnAtras = findViewById<ImageButton?>(R.id.btnVolver)
        btnAtras.setOnClickListener(View.OnClickListener{ view ->
            Util.openActivity(this, MainJobs::class.java)
        })

        val btnSearch = findViewById<ImageButton?>(R.id.btnSearch_profile)
        btnSearch?.setOnClickListener {
            searchProfile(TextID.text?.trim().toString())
        }

        TextBirthdate.setOnClickListener { showDatePickerDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_crud, menu)
        menuitemDelete = menu.findItem(R.id.menuDelete)
        menuitemDelete.isVisible = IsEditMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSave -> {
                if (IsEditMode) {
                    Util.showDialogCondition(
                        this, getString(R.string.TextSaveQuestion)
                    ) { savePerson() }
                } else {
                    savePerson()
                }
                true
            }
            R.id.menuDelete -> {
                Util.showDialogCondition(
                    this, getString(R.string.TextDeleteQuestion)
                ) { deletePerson() }
                true
            }
            R.id.menuCancel -> {
                cleanScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDateString(dayValue: Int, monthValueOneBased: Int, yearValue: Int): String {
        val d = if (dayValue < 10) "0$dayValue" else "$dayValue"
        val m = if (monthValueOneBased < 10) "0$monthValueOneBased" else "$monthValueOneBased"
        return "$d/$m/$yearValue"
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthZeroBased: Int, dayOfMonth: Int) {
        TextBirthdate.setText(getDateString(dayOfMonth, monthZeroBased + 1, year))
        this.day = dayOfMonth
        this.month = monthZeroBased
        this.year = year
    }

    private fun ResetDate() {
        val calendar = Calendar.getInstance()
        year  = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)       // 0..11
        day   = calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }

    private fun searchProfile(id: String) {
        try {
            val profile = profileController.getById(id)
            if (profile != null) {
                IsEditMode = true
                TextID.isEnabled = false
                TextName.setText(profile.Name)
                TextFLastName.setText(profile.FirstLastName)
                TextSLastName.setText(profile.SecondLastName)
                TextEmail.setText(profile.Email)
                TextPhone.setText(profile.Phone.toString())

                val b = profile.BirthDate
                TextBirthdate.setText(getDateString(b.dayOfMonth, b.month.value, b.year))
                day = b.dayOfMonth
                month = b.month.value - 1
                year = b.year

                if (::menuitemDelete.isInitialized) menuitemDelete.isVisible = true
                invalidateOptionsMenu()
            } else {
                Toast.makeText(this, getString(R.string.MsgDataNoFound), Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.ErrorMsg), Toast.LENGTH_LONG).show()
        }
    }

    private fun cleanScreen() {
        IsEditMode = false
        ResetDate()
        TextID.isEnabled = true
        TextID.setText("")
        TextName.setText("")
        TextFLastName.setText("")
        TextSLastName.setText("")
        TextEmail.setText("")
        TextPhone.setText("")
        TextBirthdate.setText("")
        if (::menuitemDelete.isInitialized) menuitemDelete.isVisible = false
        invalidateOptionsMenu()
    }

    private fun isValidationData(): Boolean {
        val phoneOk = TextPhone.text?.trim()?.toString()?.toIntOrNull()?.let { it > 0 } == true
        return TextID.text?.trim()?.isNotEmpty() == true
                && TextName.text?.trim()?.isNotEmpty() == true
                && TextFLastName.text?.trim()?.isNotEmpty() == true
                && TextSLastName.text?.trim()?.isNotEmpty() == true
                && TextEmail.text?.trim()?.isNotEmpty() == true
                && TextBirthdate.text?.trim()?.isNotEmpty() == true
                && phoneOk
    }

    private fun savePerson() {
        try {
            if (isValidationData()) {
                val exists = profileController.getById(TextID.text.toString()) != null
                if (exists && !IsEditMode) {
                    Toast.makeText(this, R.string.MsgDuplicateData, Toast.LENGTH_LONG).show()
                    return
                }

                val profile = Profile().apply {
                    ID = TextID.text.toString()
                    Name = TextName.text.toString()
                    FirstLastName = TextFLastName.text.toString()
                    SecondLastName = TextSLastName.text.toString()
                    Email = TextEmail.text.toString()
                    Phone = TextPhone.text.toString().toInt()
                }

                if (!IsEditMode)
                    profileController.addProfile(profile)
                else
                    profileController.updateProfile(profile)

                cleanScreen()
                Toast.makeText(this, R.string.MsgSaveSuccess, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.MsgMissingData, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun deletePerson() {
        try {
            profileController.removeProfile(TextID.text?.trim().toString())
            cleanScreen()
            Toast.makeText(this, getString(R.string.MsgDeleteSuccess), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
        }
    }
}
