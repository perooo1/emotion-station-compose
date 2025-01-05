package com.plenart.emotionstationcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.ui.theme.spacing

data class AuthenticationLayoutUiState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val lastName: String = "",
    val signUpAsSpecialist: Boolean = false,
)

@Composable
fun AuthenticationLayout(
    isSignUp: Boolean = false,
    onEmailChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onNavigateToSignIn: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    onPasswordChange: (String) -> Unit,
    onSignInAction: () -> Unit = {},
    onSignUpAction: () -> Unit = {},
    onSignUpAsTherapistChange: (Boolean) -> Unit = {},
    uiState: AuthenticationLayoutUiState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(R.drawable.register_icon),
            contentDescription = stringResource(R.string.authentication_train_img_content_descr),
            modifier = Modifier
                .size(dimensionResource(R.dimen.authentication_hero_image_size))
                .graphicsLayer { if (!isSignUp) scaleX = -1f },
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
                ESTextField(
                    icon = { Icon(Icons.Default.Person, contentDescription = "") },
                    label = stringResource(R.string.authentication_name_label),
                    onValueChange = onNameChange,
                    value = uiState.name,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                ESTextField(
                    icon = { Icon(Icons.Default.Person, contentDescription = "") },
                    label = stringResource(R.string.authentication_last_name_label),
                    onValueChange = onLastNameChange,
                    value = uiState.lastName,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            }
        ESTextField(
            icon = { Icon(Icons.Default.Email, contentDescription = "") },
            label = stringResource(R.string.authentication_email_label),
            onValueChange = onEmailChange,
            value = uiState.email,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        ESTextField(
            icon = { Icon(Icons.Default.Lock, contentDescription = "") },
            label = stringResource(R.string.authentication_password_label),
            onValueChange = onPasswordChange,
            value = uiState.password,
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        ElevatedButton(
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            onClick = if (isSignUp) onSignUpAction else onSignInAction,
            shape = RoundedCornerShape(dimensionResource(R.dimen.text_field_corner_radius)),
            modifier = modifier.fillMaxWidth(0.8f)
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
                            checked = uiState.signUpAsSpecialist,
                            onCheckedChange = onSignUpAsTherapistChange,
                        )
                    },
                    modifier = modifier.fillMaxWidth(0.9f)
                )

            }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (isSignUp) stringResource(R.string.authentication_already_have_an_account) else
                    stringResource(R.string.authentication_dont_have_an_account)

            )
            TextButton(
                onClick = if (isSignUp) onNavigateToSignIn else onNavigateToSignUp,
                modifier = Modifier.width(dimensionResource(R.dimen.switch_authentication_screen_button_width)),
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
