package cr.ac.utn.proyectoempleoscr

import Controller.JobController
import Entity.Job
import Util.Util
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainPostJob : AppCompatActivity() {

    private lateinit var jobController: JobController
    private var isEditMode: Boolean = false

    private lateinit var inputJobId: TextInputEditText
    private lateinit var inputPosition: TextInputEditText
    private lateinit var inputCompany: TextInputEditText
    private lateinit var inputUbi: TextInputEditText
    private lateinit var inputModality: TextInputEditText
    private lateinit var inputJornada: AutoCompleteTextView
    private lateinit var inputDescription: TextInputEditText
    private lateinit var inputRequirements: TextInputEditText
    private lateinit var inputPhone: TextInputEditText
    private lateinit var inputEmail: TextInputEditText
    private lateinit var imgLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_post_job)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        jobController = JobController(this)

        inputJobId = findViewById(R.id.inputJobId)
        inputPosition = findViewById(R.id.inputPosition)
        inputCompany = findViewById(R.id.inputCompany)
        inputUbi = findViewById(R.id.inputUbi)
        inputModality = findViewById(R.id.inputModality)
        inputJornada = findViewById(R.id.inputJornada)
        inputDescription = findViewById(R.id.inputDescription)
        inputRequirements = findViewById(R.id.inputRequirements)
        inputPhone = findViewById(R.id.inputPhone)
        inputEmail = findViewById(R.id.inputEmail)
        imgLogo = findViewById(R.id.imgLogo)

        val jornadas = listOf(
            "Tiempo completo",
            "Medio tiempo",
            "Por horas",
            "Temporal"
        )
        val adapterJornada = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            jornadas
        )
        inputJornada.setAdapter(adapterJornada)

        val btnSelectLogo = findViewById<ImageButton>(R.id.btnSelectLogo)
        btnSelectLogo.setOnClickListener {
            takePhoto()
        }

        val btnVolver = findViewById<ImageButton>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            Util.openActivity(this, MainJobs::class.java)
        }

        val btnCanc = findViewById<Button>(R.id.btnCancelar)
        btnCanc.setOnClickListener {
            Util.openActivity(this, MainJobs::class.java)
        }

        val btnPublish = findViewById<Button>(R.id.btnPublish)
        btnPublish.setOnClickListener {
            saveJob()
        }
    }

    private fun isValidatedData(): Boolean {
        return inputJobId.text?.trim()?.isNotEmpty() == true &&
                inputPosition.text?.trim()?.isNotEmpty() == true &&
                inputCompany.text?.trim()?.isNotEmpty() == true &&
                inputUbi.text?.trim()?.isNotEmpty() == true &&
                inputModality.text?.trim()?.isNotEmpty() == true &&
                inputJornada.text?.trim()?.isNotEmpty() == true &&
                inputDescription.text?.trim()?.isNotEmpty() == true &&
                inputRequirements.text?.trim()?.isNotEmpty() == true &&
                inputPhone.text?.trim()?.isNotEmpty() == true &&
                inputEmail.text?.trim()?.isNotEmpty() == true &&
                imgLogo.drawable != null
    }

    private fun cleanScreen() {
        isEditMode = false
        inputJobId.setText("")
        inputPosition.setText("")
        inputCompany.setText("")
        inputUbi.setText("")
        inputModality.setText("")
        inputJornada.setText("")
        inputDescription.setText("")
        inputRequirements.setText("")
        inputPhone.setText("")
        inputEmail.setText("")
        imgLogo.setImageDrawable(null)
    }

    private fun saveJob() {
        try {
            if (isValidatedData()) {
                val id = inputJobId.text.toString().trim()
                val existing = jobController.getById(id)

                if (existing != null && !isEditMode) {
                    Toast.makeText(this, R.string.MsgDuplicateData, Toast.LENGTH_LONG).show()
                } else {
                    val logoBitmap = (imgLogo.drawable as BitmapDrawable).bitmap

                    val job = Job(
                        id = id,
                        puesto = inputPosition.text.toString().trim(),
                        nombreEmpresa = inputCompany.text.toString().trim(),
                        ubicacion = inputUbi.text.toString().trim(),
                        modalidad = inputModality.text.toString().trim(),
                        jornadaLaboral = inputJornada.text.toString().trim(),
                        descripcion = inputDescription.text.toString().trim(),
                        requisitos = inputRequirements.text.toString().trim(),
                        logoEmpresa = logoBitmap,
                        numeroTelefono = inputPhone.text.toString().trim(),
                        correo = inputEmail.text.toString().trim()
                    )

                    if (!isEditMode) {
                        jobController.addJob(job)
                    } else {
                        jobController.updateJob(job)
                    }

                    cleanScreen()
                    Toast.makeText(this, getString(R.string.SuccessMessage), Toast.LENGTH_LONG).show()
                    Util.openActivity(this, MainJobs::class.java)
                }
            } else {
                Toast.makeText(this, R.string.MsgMissingData, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteJob() {
        try {
            val id = inputJobId.text.toString().trim()
            if (id.isNotEmpty()) {
                jobController.removeJob(id)
                cleanScreen()
                Toast.makeText(this, R.string.MsgDeleteSucess, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
        }
    }

    private val cameraPreviewLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            if (bitmap != null) {
                imgLogo.setImageBitmap(bitmap)
            }
        }

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let { imageUri ->
                    imgLogo.setImageURI(imageUri)
                }
            }
        }

    private fun takePhoto() {
        cameraPreviewLauncher.launch(null)
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.type = "image/*"
        selectImageLauncher.launch(intent)
    }
}
