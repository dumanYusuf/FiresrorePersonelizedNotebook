import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.model.toMap
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : NoteRepo {

    override suspend fun addNote(notes: Notes): Resource<Notes> {
        return try {
            val userId = firebaseAuth.currentUser?.uid

            if (userId != null) {
                val userDocumentRef = firestore.collection("Users").document(userId)

                val documentRef = userDocumentRef.collection("Notes").add(notes).await()

                val newNote = notes.copy(noteId = documentRef.id)

                userDocumentRef.collection("Notes").document(documentRef.id).set(newNote.toMap()).await()

                Resource.Success(newNote)
            } else {
                Resource.Error("User not found")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }

}

