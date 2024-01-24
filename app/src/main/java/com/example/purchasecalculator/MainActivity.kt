@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.purchasecalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.purchasecalculator.constants.Constants
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.model.ProductMock
import com.example.purchasecalculator.ui.theme.PurchaseCalculatorTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                ProductList(ProductMock.productList)
                FloatingActionButton()
            }

//            PurchaseCalculatorTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
////                    ProductList()
//                    FloatingActionButton()
//
//                }
//            }
        }
    }

}

@Composable
fun ProductList(products: List<Product>) {
    Surface (Modifier.fillMaxSize()){
        LazyColumn {
            items(products){
                ProductRow(product = it)
            }
        }
        FloatingActionButton()
    }
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
fun FloatingActionButton() {

    val activity = LocalContext.current

    FloatingActionButton(
        onClick = { activity.startActivity(Intent(activity, FormActivity::class.java)) },
        modifier = Modifier
            .padding(32.dp)
            .wrapContentSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFloatingActionButton() {
    FloatingActionButton()
}