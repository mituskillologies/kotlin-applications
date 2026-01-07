package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ActivityMainBinding

// Data class to hold our shopping item information
data class ShoppingItem(val name: String, val quantity: Int)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val shoppingList = ArrayList<ShoppingItem>()
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    // New way to handle activity results
    private val addItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val itemName = data?.getStringExtra("ITEM_NAME")
            val itemQuantity = data?.getIntExtra("ITEM_QUANTITY", 0) ?: 0

            if (itemName != null && itemQuantity > 0) {
                shoppingList.add(ShoppingItem(itemName, itemQuantity))
                shoppingListAdapter.notifyItemInserted(shoppingList.size - 1)
                updateEmptyState()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        updateEmptyState()

        // FloatingActionButton to launch the AddItemActivity
        binding.fabAddItem.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            addItemLauncher.launch(intent)
        }
    }

    private fun setupRecyclerView() {
        shoppingListAdapter = ShoppingListAdapter(shoppingList)
        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = shoppingListAdapter
        }
    }

    private fun updateEmptyState() {
        if (shoppingList.isEmpty()) {
            binding.recyclerViewItems.visibility = View.GONE
            binding.textViewEmptyList.visibility = View.VISIBLE
        } else {
            binding.recyclerViewItems.visibility = View.VISIBLE
            binding.textViewEmptyList.visibility = View.GONE
        }
    }
}

// Adapter for the RecyclerView
class ShoppingListAdapter(private val items: List<ShoppingItem>) : RecyclerView.Adapter<ShoppingListAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewItemName)
        val quantityTextView: TextView = itemView.findViewById(R.id.textViewItemQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_shopping, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.nameTextView.text = currentItem.name
        holder.quantityTextView.text = "Qty: ${currentItem.quantity}"
    }

    override fun getItemCount() = items.size
}
