import com.example.personelnotebookcleanarchitecture.domain.model.Notes
import com.example.personelnotebookcleanarchitecture.domain.model.User
import com.example.personelnotebookcleanarchitecture.domain.model.toMap
import com.example.personelnotebookcleanarchitecture.domain.repo.NoteRepo
import com.example.personelnotebookcleanarchitecture.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun getNotes(): Flow<Resource<List<Notes>>> = flow{
        try {
            val userId=firebaseAuth.currentUser?.uid
            if (userId!=null){
                val userDocumentRef=firestore.collection("Users").document(userId)
               val snapNote =userDocumentRef.collection("Notes").get().await()// verileri alırken get kullandım,listenerda kullanabilirdim
                val noteList=snapNote.documents.map {documentSnapshot ->  
                    documentSnapshot.toObject(Notes::class.java)?.copy(noteId = documentSnapshot.id)
                }.filterNotNull()
                emit(Resource.Success(noteList))
            }
            else{
                emit(Resource.Error("User not found"))
            }
        }
        catch (e:Exception){
            emit(Resource.Error("Error:${e.message}"))
        }
    }

    override suspend fun deleteNote(note: Notes): Resource<Notes> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val userNoteDocumentRef = firestore.collection("Users").document(userId)
                    .collection("Notes").document(note.noteId)

                userNoteDocumentRef.delete().await()
                Resource.Success(note)
            } else {
                Resource.Error("User not found")
            }
        } catch (e: Exception) {
            Resource.Error("Error deleting note: ${e.message}")
        }
    }

    override suspend fun updateNote(notes: Notes): Resource<Notes> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val userNotesDocument = firestore.collection("Users").document(userId)
                    .collection("Notes").document(notes.noteId)

                userNotesDocument.update(notes.toMap()).await() //set de kullanabiliriz
                Resource.Success(notes)
            } else {
                Resource.Error("User not found")
            }
        } catch (e: Exception) {
            Resource.Error("Error updating note: ${e.message}")
        }
    }

    override fun searchNotes(searchNote: String): Flow<Resource<List<Notes>>> = flow {
        try {
            emit(Resource.Loading())
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val userNoteDocument = firestore.collection("Users").document(userId)
                    .collection("Notes")

                val snapshot = userNoteDocument.get().await()

                val searchList = snapshot.documents.mapNotNull { it.toObject(Notes::class.java) }

                val filteredNotes = searchList.filter { note ->
                    note.title.contains(searchNote, ignoreCase = true)
                }

                emit(Resource.Success(filteredNotes))
            } else {
                emit(Resource.Error("Kullanıcı kimliği alınamadı"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Hata: ${e.message}"))
        }
    }

    override suspend fun getUsers(): Resource<List<User>> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val userDocument = firestore.collection("Users").document(userId).get().await()
                val user = userDocument.toObject(User::class.java)
                if (user != null) {
                    Resource.Success(listOf(user))
                } else {
                    Resource.Error("User not found")
                }
            } else {
                Resource.Error("User is not logged in")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.localizedMessage}")
        }
    }

}

