package com.example.purchasecalculator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.model.SingleProductMock
import com.example.purchasecalculator.viewModel.ProductViewModel


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ProductViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        setContent {
            MyApp(this, viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }
}

@Composable
fun ProductRow(product: Product, viewModel: ProductViewModel? = null) {

    val id = product.id
    val store = product.store
    val name = product.name
    val value = product.value
    val quantity = product.quantity
    val type = product.type
    val pricePerUnit = product.calculatePricePerUnit()

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { }
    ) {
        Column(
            Modifier
                .weight(2f)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    fontSize = 24.sp
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                if (store != null) {
                    Text(text = store)
                }
                Text(text = " · $quantity")
                Text(text = " $type")
                Text(text = " · R$ $value")
            }

        }

        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "R$ $pricePerUnit",
                    fontSize = 24.sp
                )
            }
            Row {
                Text(text = "/$type")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProductRow() {
    ProductRow(SingleProductMock)
}

@Composable
fun MyApp(context: Context, viewModel: ProductViewModel) {

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = { deleteAll(viewModel) }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_delete),
                            contentDescription = null
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            val intent = Intent(context, FormActivity::class.java)
                            context.startActivity(intent)
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(viewModel.getAll()) {
                ProductRow(product = it)
            }
        }
    }
}

@Composable
@Preview
fun DeleteAlert() {
    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog.value = false },
            title = { Text(text = "Delete?") },
            text = { Text(text = "Do you wish to confirm the deletion of the items? This action cannot be undone.") },

            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false

                    }
                ) {
                    Text(text = "CONFIRM")
                }
            },

            dismissButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(text = "CANCEL")
                }
            },
        )

    }
}

fun deleteAll(viewModel: ProductViewModel){
    viewModel.deleteAll()
}
