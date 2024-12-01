package com.plenart.emotionstationcompose.ui.authentication.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.ui.theme.spacing

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.authentication_train_img_content_descr),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(text = stringResource(R.string.authentication_sign_in_hero_label))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            OutlinedTextField(
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "") },
                label = { Text(text = stringResource(R.string.authentication_email_label)) },
                value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            OutlinedTextField(
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "") },
                label = { Text(text = stringResource(R.string.authentication_password_label)) },
                value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            ElevatedButton(modifier = Modifier, onClick = {}) {
                Text(text = stringResource(R.string.authentication_sign_in_button_label))
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.authentication_dont_have_an_account))
                TextButton(modifier = Modifier.width(80.dp), onClick = {
                }) {
                    Text(text = stringResource(R.string.authentication_sign_up_here_button_label))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}
