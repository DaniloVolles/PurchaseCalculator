package com.example.purchasecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.purchasecalculator.ui.theme.PurchaseCalculatorTheme
import com.example.purchasecalculator.util.PriceCalculator
import com.example.purchasecalculator.viewModel.InclusionFormViewModel

class FormActivity : ComponentActivity() {

    private lateinit var viewModel: InclusionFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[InclusionFormViewModel::class.java]

        setContent {
            PurchaseCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormSheet()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSheet() {

    lateinit var viewModel: InclusionFormViewModel

    var store by remember { mutableStateOf("") }
    var product by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var pricePerUnit by remember { mutableStateOf("") }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            OutlinedTextField(
                value = store,
                onValueChange = {
                    if (it.length <= 20) {
                        store = it
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                label = { Text("Loja") },
                supportingText = { Text("Opcional") }
            )
            OutlinedTextField(
                value = product,
                onValueChange = {
                    if (it.length <= 20) {
                        product = it
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                label = { Text("Produto") },
                supportingText = { Text("Opcional") }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = price,
                onValueChange = {
                    if (it.length <= 10) { // Only 10 digit input
                        price = it
                    }
                },
                modifier = Modifier
                    .weight(4f)
                    .padding(4.dp),
                label = { Text("Valor") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
            OutlinedTextField(
                amount,
                { amount = it },
                Modifier
                    .weight(4f)
                    .padding(4.dp),
                label = { Text("Quant.") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            var expanded by remember { mutableStateOf(false) }
            val list = listOf("L", "g", "un.", "m")
            var type by remember { mutableStateOf(list[0]) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                Modifier
                    .weight(3f)
                    .padding(4.dp),
            ) {

                OutlinedTextField(

                    value = type,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    onValueChange = {},
                    readOnly = false,
                    label = { Text("Medida") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    list.forEach { selectedOption ->
                        DropdownMenuItem(
                            text = { Text(selectedOption) },
                            onClick = {
                                type = selectedOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, bottom = 64.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = { price = ""; amount = ""; store = ""; product = ""; pricePerUnit = "" }
            ) {
                Text(text = "limpar", fontSize = 16.sp)
            }
            Button(
                onClick = { pricePerUnit = PriceCalculator.calculatePricePerUnit(price, amount) }
            ) {
                Text(
                    text = "calcular",
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    viewModel.insert(1, store, product, price.toDouble(), amount.toDouble(), type)
                }
            ) {
                Text(text = "salvar", fontSize = 16.sp)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = (pricePerUnit),
                fontSize = 28.sp,
            )
        }
    }

}

fun saveButton () {

}

@Preview(showBackground = true)
@Composable
fun PreviewFormSheet (){
    FormSheet()
}
