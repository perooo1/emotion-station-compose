package com.plenart.emotionstationcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.ui.theme.spacing

data class AuthenticationLayoutUiState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val lastName: String = "",
    val signInAsTherapist: Boolean = false,
)

@Composable
fun AuthenticationLayout(
    uiState: AuthenticationLayoutUiState,
    isSignUp: Boolean = false,
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToSignIn: () -> Unit = {},
    onSignUpAction: () -> Unit = {},
    onSignInAction: () -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onLastNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpAsTherapistChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.authentication_train_img_content_descr),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text =
            if (isSignUp)
                stringResource(R.string.authentication_sign_up_hero_label)
            else
                stringResource(R.string.authentication_sign_in_hero_label)
        )
        if (isSignUp)
            Column {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                OutlinedTextField(
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "") },
                    label = { Text(text = stringResource(R.string.authentication_name_label)) },
                    value = uiState.name,
                    onValueChange = onNameChange,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                OutlinedTextField(
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "") },
                    label = { Text(text = stringResource(R.string.authentication_last_name_label)) },
                    value = uiState.lastName,
                    onValueChange = onLastNameChange,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            }
        OutlinedTextField(
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "") },
            label = { Text(text = stringResource(R.string.authentication_email_label)) },
            value = uiState.email,
            onValueChange = onEmailChange,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        OutlinedTextField(
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "") },
            label = { Text(text = stringResource(R.string.authentication_password_label)) },
            value = uiState.password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = onPasswordChange,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        ElevatedButton(
            onClick = if (isSignUp) onSignUpAction else onSignInAction
        ) {
            Text(
                text = if (isSignUp) stringResource(R.string.authentication_sign_up_here_button_label) else
                    stringResource(R.string.authentication_sign_in_here_button_label)
            )
        }
        if (isSignUp)
            Column {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                ListItem(
                    headlineContent = {
                        Text(text = stringResource(R.string.authentication_sign_up_as_therapist))
                    },
                    trailingContent = {
                        Switch(
                            checked = uiState.signInAsTherapist,
                            onCheckedChange = onSignUpAsTherapistChange,
                        )
                    },
                )

            }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (isSignUp) stringResource(R.string.authentication_already_have_an_account) else
                    stringResource(R.string.authentication_dont_have_an_account)

            )
            TextButton(
                modifier = Modifier.width(80.dp),
                onClick = if (isSignUp) onNavigateToSignIn else onNavigateToSignUp,
            ) {
                Text(
                    text =
                    if (isSignUp)
                        stringResource(R.string.authentication_sign_in_here_button_label)
                    else
                        stringResource(R.string.authentication_sign_up_here_button_label)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CredentialsInputLayoutPreview(modifier: Modifier = Modifier) {
    val uiState = AuthenticationLayoutUiState()
    AuthenticationLayout(
        uiState = uiState,
        isSignUp = true,
        onLastNameChange = {},
        onNavigateToSignIn = {},
        onPasswordChange = {},
        onNavigateToSignUp = {},
        onSignInAction = {},
        onSignUpAction = {},
        onEmailChange = {},
        onNameChange = {},
    )
}
