import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepositoryImpl
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val authenticationModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            firebaseAuth = Firebase.auth,
            databaseRepository = get(),
            coroutineScope = CoroutineScope(Dispatchers.IO),
        )
    }
}
