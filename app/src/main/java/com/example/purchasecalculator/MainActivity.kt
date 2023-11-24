@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.purchasecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.purchasecalculator.ui.theme.PurchaseCalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PurchaseCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ModalBottomSheetForm()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetForm() {
    val sheetState = rememberModalBottomSheetState()
    var isFormOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { isFormOpen = true },
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
    if (isFormOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isFormOpen = false }
        ) {
            FormSheet()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSheet() {

    var where by remember { mutableStateOf("") }
    var which by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            OutlinedTextField(
                value = where,
                onValueChange = {
                    if (it.length <= 20) {
                        where = it
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                label = { Text("Onde") },
                supportingText = { Text("Opcional") }
            )
            OutlinedTextField(
                value = which,
                onValueChange = {
                    if (it.length <= 20) {
                        which = it
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
            var selectedItem by remember { mutableStateOf(list[0]) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                Modifier
                    .weight(3f)
                    .padding(4.dp),
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    onValueChange = {},
                    readOnly = false,
                    value = selectedItem,
                    label = { Text("Tipo") },
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
                                selectedItem = selectedOption
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
            Text(
                text = "R$ 0 /L",
                fontSize = 28.sp
            )
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "calcular", fontSize = 16.sp)
            }
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "salvar", fontSize = 16.sp)
            }
        }
    }

}

@Composable
fun TypeDropDownMenu() {

}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewDropDownMenu() {
    TypeDropDownMenu()
}

@Composable
@Preview
fun PreviewFormSheet() {
    FormSheet()
}

@Composable
@Preview
fun PreviewModalBottomSheetForm() {
    ModalBottomSheetForm()
}

@Composable
fun Btn_OpenForm() {
    Button(
        onClick = { },
        modifier = Modifier
            .size(16.dp)
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
