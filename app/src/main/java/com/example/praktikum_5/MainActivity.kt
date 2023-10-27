 package com.example.praktikum_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum_5.Data.DataForm
import com.example.praktikum_5.Data.DataSource.jenis
import com.example.praktikum_5.Data.DataSource.status
import com.example.praktikum_5.ui.theme.Praktikum_5Theme

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktikum_5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            Form()
        }
    }
}

 @OptIn(ExperimentalMaterial3Api :: class)
 @Composable
 fun Form(CobaViewModel: CobaViewModel = viewModel()){
     var textNama by remember { mutableStateOf("") }
     var textTlp by remember { mutableStateOf("") }
     var textAlmt by remember { mutableStateOf("") }
     var textEmail by remember { mutableStateOf("") }


     val context = LocalContext.current
     val dataForm : DataForm
     val uiState by CobaViewModel.uiState.collectAsState()
     dataForm = uiState
     
     Row {
         Text(text = "Register", fontWeight = FontWeight.Bold)


     }
     Text(text = "Create Your Account", fontWeight = FontWeight.Bold, fontSize = 25.sp)

     OutlinedTextField(
         value = textNama,
         singleLine = true,
         shape = MaterialTheme.shapes.large,
         modifier = Modifier.fillMaxWidth(),
         label = {Text(text = "Nama Lengkap")},
         onValueChange = {textNama = it})

     OutlinedTextField(
         value = textTlp,
         singleLine = true,
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
         shape = MaterialTheme.shapes.large,
         modifier = Modifier.fillMaxWidth(),
         label = {Text(text = "Nomor Telepon")},
         onValueChange = {textTlp = it})

     OutlinedTextField(
         value = textEmail,
         singleLine = true,
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
         shape = MaterialTheme.shapes.large,
         modifier = Modifier.fillMaxWidth(),
         label = {Text(text = "Email")},
         onValueChange = {textEmail = it})

     PilihJK(
         options = jenis.map { id -> context.resources.getString(id)},
         onSelectionChanged = {CobaViewModel.setJenisKelamin(it)}
     )
     Pilihsts(
         options = status.map { id -> context.resources.getString(id)},
         onSelectionChanged = {CobaViewModel.setStatusPernikahan(it)}
     )

     OutlinedTextField(
         value = textAlmt,
         singleLine = true,
         shape = MaterialTheme.shapes.large,
         modifier = Modifier.fillMaxWidth(),
         label = {Text(text = "Alamat Lengkap")},
         onValueChange = {textAlmt = it})

     Button(
         modifier = Modifier.fillMaxWidth(),
         onClick = {
             CobaViewModel.BacaData(textNama, textTlp, textAlmt,textEmail,dataForm.sex,dataForm.status)
         }
     ){
         Text(text = stringResource(R.string.submit),
             fontSize = 16.sp)
     }
     Spacer(modifier = Modifier.height(5.dp))
     Hasil(namahs = CobaViewModel.namauser , teleponhs = CobaViewModel.notelepon, jenishs = CobaViewModel.jeniskelamin , alamaths = CobaViewModel.alamat, emailhs = CobaViewModel.email, statushs = CobaViewModel.status )
 }
 
 @Composable
 fun PilihJK(
     options: List<String>,
     onSelectionChanged: (String) -> Unit = {}
 ){
     var selectedValue by rememberSaveable { mutableStateOf("")}
     Column(modifier = Modifier.padding(16.dp)){
         Text(text = "Jenis Kelamin :")
         options.forEach{ item -> 
             Row (
                 modifier = Modifier.selectable(
                     selected = selectedValue == item, 
                     onClick = {
                         selectedValue = item 
                         onSelectionChanged(item)}),
                 verticalAlignment = Alignment.CenterVertically
             ){
                 RadioButton(
                     selected = selectedValue == item,
                     onClick = {
                         selectedValue = item
                         onSelectionChanged(item)})
                 Text(item)
             }
         }
     }
 }

 @Composable
 fun Pilihsts(
     options: List<String>,
     onSelectionChanged: (String) -> Unit = {}){
     var selectedValue by rememberSaveable { mutableStateOf("")}
     Column(modifier = Modifier.padding(16.dp)){
         Text(text = "Status :")
         options.forEach{ item ->
             Row (
                 modifier = Modifier.selectable(
                     selected = selectedValue == item,
                     onClick = {
                         selectedValue = item
                         onSelectionChanged(item)}),
                 verticalAlignment = Alignment.CenterVertically
             ){
                 RadioButton(
                     selected = selectedValue == item,
                     onClick = {
                         selectedValue = item
                         onSelectionChanged(item)})
                 Text(item)
             }
         }
     }
 }



 @Composable
 fun Hasil(namahs:String, teleponhs:String, jenishs:String, alamaths:String, statushs:String, emailhs:String){
     ElevatedCard(
         elevation = CardDefaults.cardElevation(
             defaultElevation = 6.dp
         ),
         modifier = Modifier
             .size(width = 300.dp, height = 200.dp)
     ) {
         Text(text = "Jenis Kelamin : " + jenishs,
             modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
         Text(text = "Status : " + statushs,
             modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
         Text(text = "Alamat : " + alamaths,
             modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
         Text(text = "Email : " + emailhs,
             modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
     }
 }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum_5Theme {
        HomePage()
    }
}