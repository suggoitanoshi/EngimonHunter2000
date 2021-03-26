#ifndef _INVENTORY_CPP
#define _INVENTORY_CPP
#include "../headers/BaseInventory.hpp"
#include "../headers/InventoryException.hpp"
#include "iostream"
#include "vector"
#include "algorithm"

template <class T>
class Inventory : BaseInventory {
private:
    /* data */
    vector<T> cont;
    int items;

public:
    /**
     * Konstruktor Inventory
     */
    Inventory() : BaseInventory() {
        this->cont = vector<T>();
        items = 0;
    }
    /**
     * Tambah item ke inventory
     */
    void addItem(T& item) {
        if (this->getTotalItemCount() < this->getMaxCapacity()) {
            this->cont.push_back(item);
            this->incrementItem();
            items++;
        } else {
            throw InventoryException(0);  // inventory tidak cukup
        }
    }
    void addItemNoDupe(T& item){
        if(std::find(cont.begin(), cont.end(), item) == cont.end()){
            addItem(item);
        }
        else{
            if(this->getTotalItemCount() < this->getMaxCapacity()){
                this->incrementItem();
                items++;
            }
            else{
                throw InventoryException(0);
            }
        }
    }
    /**
     * Menghapus item `item` pertama
     */
    void removeItem(T item) {
        int i;
        try {
            i = getFirstItemIndex(item);
            this->subtractItem();
            this->cont.erase(cont.begin() + i);
        } catch (InventoryException&) {
            throw;
        }
    }
    /**
     * Dapatkan index item `item` pertama
     */
    int getFirstItemIndex(T item) {
        size_t i;
        if (this->getTotalItemCount() > 0) {
            i = 0;
            while (i < this->cont.size()) {
                if (this->cont.at(i) == item) {
                    return i;
                }
                i++;
            }
            throw InventoryException(2);
        } else {
            throw InventoryException(1);
        }
    }
    /**
     * menampilkan isi inventory
     */
    void showInventory() const {
        size_t i = 0;
        for (i = 0; i < this->cont.size(); i++) {
            cout << i+1 << ". ";
            cout << this->cont.at(i) << endl;
        }
    }
    /**
     * operator indexing
     */
    T& operator[](int i) {
        try {
            return this->cont.at(i);
        } catch (const out_of_range& e) {
            throw InventoryException(3);
        }
    }
    /**
     * mengembalikan jumlah item di inventory ini
     */
    int getItemCount() const { return items; }

    ~Inventory() {
        int i;
        for (i = 0; i < this->getItemCount(); i++) {
            this->subtractItem();
        }
    }
};
#endif  // _INVENTORY_CPP
