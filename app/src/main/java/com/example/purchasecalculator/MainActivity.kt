@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.purchasecalculator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.purchasecalculator.constants.Constants
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.model.ProductMock
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(this)
        }
    }

}

@Composable
fun ProductList(products: List<Product>) {

}

@Composable
fun ProductRowMock() {

    val product = Product(
        108,
        "Assaí",
        "Coca-Cola",
        12.99,
        3.0,
        Constants.PRODUCT.TYPE.LITERS
    )
    ProductRow(product)
}

@Composable
fun ProductRow(product: Product) {

    val store = product.store
    val name = product.name
    val value = product.value
    val quantity = product.quantity
    val type = product.type
    val pricePerUnit = "R$4.33 "

    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            Modifier
                .weight(3f)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name)
                Text(text = type.toString())
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (store != null) {
                    Text(text = store)
                }
                Text(text = value.toString())
                Text(text = quantity.toString())
            }

        }

        Column(
            Modifier.weight(1f)
        ) {
            Text(text = pricePerUnit)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProductRow() {
    ProductRowMock()
}

@Composable
fun MyApp(context: Context) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Aqui será implementada a limpeza do BD.") }
                    }) {
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
            items(ProductMock.productList) {
                ProductRow(product = it)
            }
        }
    }
}

