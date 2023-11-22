@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.purchasecalculator

import android.os.Bundle
import android.widget.EditText
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.purchasecalculator.ui.theme.PurchaseCalculatorTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


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

@Composable
fun FormSheet() {

    var where by remember { mutableStateOf("") }
    var which by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = where,
                onValueChange = {
                    if (it.length <= 20) {
                        price = it
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                label = { Text("Onde") },
                supportingText = { Text("Opcional")}
            )
            OutlinedTextField(
                value = which,
                onValueChange = {
                    if (it.length <= 20){
                        price = it
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                label = { Text("Produto") },
                supportingText = { Text("Opcional")}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    .padding(16.dp)
                    .weight(4f),
                label = { Text("Valor") }

            )
            OutlinedTextField(
                amount,
                { amount = it },
                Modifier
                    .padding(16.dp)
                    .weight(3f),
                label = { Text("Quant.") }
            )
            TypeDropDownMenu(

            )
        }
    }

}

@Composable
fun TypeDropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var list = listOf("1", "2", "3")


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
