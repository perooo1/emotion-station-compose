import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepositoryImpl
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import org.koin.dsl.module

val authenticationModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            firebaseAuth = Firebase.auth
        )
    }
}
