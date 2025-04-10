package com.example.myapplicationf11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationf11.dataPilots.pilotList
import com.example.myapplicationf11.dataPilots.Pilots
import com.example.myapplicationf11.ui.theme.F1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Formula1App()
                }
            }
        }
    }
}

@Composable
fun Formula1App() {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var hobbies by remember { mutableStateOf("") }
    var pilotoEnEdicion by remember { mutableStateOf<Pilots?>(null) }

    Scaffold(
        topBar = { TopAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                pilotoEnEdicion = null
                name = ""
                age = ""
                hobbies = ""
                showDialog = true
            }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Agregar piloto")
            }
        }
    ) { paddingValues ->

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        if (name.isNotBlank() && age.isNotBlank() && hobbies.isNotBlank()) {
                            if (pilotoEnEdicion == null) {
                                pilotList.add(
                                    Pilots(
                                        imageResourceId = R.drawable.f1logo,
                                        name = name,
                                        age = age.toIntOrNull() ?: 0,
                                        hobbies = hobbies
                                    )
                                )
                            } else {
                                val index = pilotList.indexOf(pilotoEnEdicion)
                                if (index != -1) {
                                    pilotList[index] = pilotoEnEdicion!!.copy(
                                        name = name,
                                        age = age.toIntOrNull() ?: 0,
                                        hobbies = hobbies
                                    )
                                }
                            }
                            showDialog = false
                        }
                    }) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text(if (pilotoEnEdicion == null) "Agregar piloto" else "Editar piloto") },
                text = {
                    Column {
                        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
                        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Edad") })
                        OutlinedTextField(value = hobbies, onValueChange = { hobbies = it }, label = { Text("Hobbies") })
                    }
                }
            )
        }

        LazyColumn(contentPadding = paddingValues) {
            items(pilotList) { piloto ->
                pilotoItem(
                    pilots = piloto,
                    onEdit = {
                        pilotoEnEdicion = piloto
                        name = piloto.name
                        age = piloto.age.toString()
                        hobbies = piloto.hobbies
                        showDialog = true
                    },
                    onDelete = { pilotList.remove(piloto) },
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun pilotoItem(
    pilots: Pilots,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                pilotoIcon(pilots.imageResourceId)
                pilotoInformation(pilots.name, pilots.age)
                Spacer(Modifier.weight(1f))
                pilotoItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                pilotoHobby(pilots.hobbies)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_small)),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onEdit) { Text("Editar") }
                    TextButton(onClick = onDelete) { Text("Eliminar") }
                }
            }
        }
    }
}

@Composable
fun pilotoInformation(pilotoName: String, pilotoAge: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = pilotoName,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(text = "Edad: $pilotoAge", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun pilotoHobby(pilotoHobby: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.padding_small))) {
        Text(
            text = stringResource(R.string.about_pilot),
            style = MaterialTheme.typography.labelSmall
        )
        Text(text = pilotoHobby, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun pilotoItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.f1logo),
                    contentDescription = null
                )
                Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
            }
        },
        modifier = modifier
    )
}

@Composable
fun pilotoIcon(@DrawableRes pilotoIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(pilotoIcon),
        contentDescription = null
    )
}

@Preview
@Composable
fun pilotoPreview() {
    F1Theme(darkTheme = false) {
        Formula1App()
    }
}

@Preview
@Composable
fun pilotoDarkThemePreview() {
    F1Theme(darkTheme = true) {
        Formula1App()
    }
}
