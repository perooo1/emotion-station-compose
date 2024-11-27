import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepositoryImpl
import com.plenart.emotionstationcompose.data.authentication.IAuthenticationRepository
import org.koin.dsl.module

val authenticationModule = module {
    single<IAuthenticationRepository> {
        AuthenticationRepositoryImpl(
            firebaseAuth = Firebase.auth
        )
    }
}
