package com.example.studentform

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentFormScreen()
        }
    }
}

@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    val bgColor = Color(0xFF0A1A0A)
    val cardColor = Color(0xFF1A2E1A)
    val goldColor = Color(0xFFFFD700)
    val greenColor = Color(0xFF2E7D32)
    val textColor = Color(0xFFFFFFFF)
    val hintColor = Color(0xFFA5D6A7)

    val directions = listOf("Android", "CyberSecurity", "iOS", "DevOps", "Web")

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            dateState = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "=> რეგისტრაცია <=",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = goldColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = "შეავსე ყველა ველი",
                fontSize = 14.sp,
                color = Color(0xFFA5A5A5),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))


            OutlinedTextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("სახელი და გვარი", color = hintColor) },
                placeholder = { Text("მაგ: გიორგი ირემაძე", color = hintColor.copy(alpha = 0.6f)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = greenColor,
                    unfocusedBorderColor = greenColor.copy(alpha = 0.4f),
                    cursorColor = goldColor,
                    focusedContainerColor = cardColor,
                    unfocusedContainerColor = cardColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))


            // ელ-ფოსტა
            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text("ელ-ფოსტა", color = hintColor) },
                placeholder = { Text("giorgi.iremadze@gmail.com", color = hintColor.copy(alpha = 0.6f)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = greenColor,
                    unfocusedBorderColor = greenColor.copy(alpha = 0.4f),
                    cursorColor = goldColor,
                    focusedContainerColor = cardColor,
                    unfocusedContainerColor = cardColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .border(1.dp, greenColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .clickable { datePickerDialog.show() }
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(
                    text = if (dateState.isEmpty()) "📅  თარიღი (DD/MM/YYYY)" else "📅  $dateState",
                    color = if (dateState.isEmpty()) hintColor else textColor,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .border(1.dp, greenColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "ფავორიტი მიმართულება",
                    color = goldColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                directions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (selectedOption == option)
                                    greenColor.copy(alpha = 0.2f)
                                else Color.Transparent
                            )
                            .clickable { selectedOption = option }
                            .padding(vertical = 6.dp, horizontal = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = goldColor,
                                unselectedColor = hintColor
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option, color = textColor, fontSize = 15.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .border(1.dp, greenColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ვეთანხმები წესებსა და პირობებს",
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = goldColor,
                        checkedTrackColor = greenColor,
                        uncheckedThumbColor = Color(0xFFA5A5A5),
                        uncheckedTrackColor = Color(0xFF2A2A2A)
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(greenColor, Color(0xFF1B5E20))
                        )
                    )
                    .clickable {
                        if (nameState.isBlank() || emailState.isBlank() ||
                            dateState.isBlank() || selectedOption.isEmpty() || !isAgreed
                        ) {
                            Toast.makeText(context, "შეავსეთ ყველა ველი!", Toast.LENGTH_SHORT).show()
                        } else if (!emailState.contains("@")) {
                            Toast.makeText(context, "იმეილი არასწორია!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "მონაცემები გაიგზავნა!", Toast.LENGTH_SHORT).show()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "გაგზავნა",
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}