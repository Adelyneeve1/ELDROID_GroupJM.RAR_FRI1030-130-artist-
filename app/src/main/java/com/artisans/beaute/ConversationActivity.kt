package com.artisans.beaute

import android.os.Bundle
import android.widget.ImageButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConversationActivity : AppCompatActivity() {

    private lateinit var messagesAdapter: MessagesAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        val contactName = intent.getStringExtra("contactName")

        findViewById<TextView>(R.id.conversationTextView).text = "$contactName"

        val recyclerView: RecyclerView = findViewById(R.id.messagesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        messagesAdapter = MessagesAdapter(messages)
        recyclerView.adapter = messagesAdapter

        val sendButton: ImageButton = findViewById(R.id.sendButton)
        val messageInput: EditText = findViewById(R.id.messageInput)

        sendButton.setOnClickListener {
            val messageContent = messageInput.text.toString()
            if (messageContent.isNotBlank()) {
                val message = Message(messageContent, true)
                messages.add(message)
                messagesAdapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
                messageInput.text.clear()
            }
        }
    }
}
