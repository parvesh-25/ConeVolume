package com.altaf.conevolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.altaf.cubevolume.R

class MainActivity : AppCompatActivity(), View.OnClickListener { // onClickListener adalah listener yang kita
// implementasikan untuk memantau kejadian klik pada button



    //EditText berfungsi untuk mengambil inputan dari user
    private lateinit var edtJari: EditText
    private lateinit var edtHeight: EditText


    //Button berufungsi sebagai tombol yang bisa di klik
    private lateinit var btnCalculate: Button
    //TextView berfumngsi untuk menampilkan teks
    private lateinit var tvResult: TextView

    //views
    //Kode di atas mendeklarasikan semua komponen view yang akan dimanipulasi.
    // Kita deklarasikan secara global agar bisa dikenal di keseluruhan bagian kelas.

    companion object {
        private const val STATE_RESULT = "State_result"
    }


    //OnCreate
    // Metode oncreate berfungsi untuk mengatur layout xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Maksud baris di atas adalah kelas MainActivity akan menampilkan tampilan yang berasal dari layout activity_main.xml.

        edtJari = findViewById(R.id.edt_r)
        //casting view
        //Maksud dari baris diatas adalah
        //EditTextedtWidth disesuaikan (cast) dengan komponen EditText ber-ID edt_width
        // di layout activity_main.xml melalui metode findViewById().
        edtHeight = findViewById(R.id.edt_height)

        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)
        //keyword this merujuk objek activity saat ini yang telah mengimplementasikan OnClickListener sebelumnya
        //sehingga ketika btnCalculate diklik, maka fungsi onClick akan dipanggil dan melakukan proses yang didalamnya

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {//Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur.
        //hasil perhitungan yang ditampilkan pada tvResult dimasukkan pada bundle kemudian disimpan isinya
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
        //STATE_RESULT sebagai key dan isi dari tvResult sebagai value
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputJari = edtJari.text.toString().trim()
            //Sintaks .text.toString() di atas berfungsi untuk mengambil isi dari sebuah EditText kemudian menyimpannya dalam sebuah variabel.

            //tambahan .trimm() berfungsi untuk menghiraukan spasi jika ada, sehingga nilai yang didapat hanya berupa angka.
            val inputHeight = edtHeight.text.toString().trim()

            //Codingan agar ada notif field tidak boleh kosong
            var isEmptyFields = false

            if (inputJari.isEmpty()) { // .isEmpty() berfungsi untuk mengecek apakah inputan dari Edtitext masih kosong
                isEmptyFields = true
                edtJari.error = "Field ini tidak boleh kosong" // dan menampilkan edtLength.error untuk menampilkan pesan error
                //dan mengganti variabel boolean isEmptyField menjadi true supaya bisa lanjut ke proses berikutnya
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true;
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            //perhitungan volumenya
            if (!isEmptyFields) {

                val volume = inputJari.toDouble()* inputJari.toDouble() * inputHeight.toDouble() * 22 / 7  / 3
                //karena yg didapat dari EditText Berupa String makanya harus diuabah ke double dengan toDouble() agar bisa di hitung
                tvResult.text = volume.toString()
                //Langkah terakhir yaitu menampilkan hasil perhitungan pada TExtView tvResult dengan menggunakan .text
                //kemudian mengubah yg sebelumnya Double menjadi string dengan toString()
            }
        }
    }
}