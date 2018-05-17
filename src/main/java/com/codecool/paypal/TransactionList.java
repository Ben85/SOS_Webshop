package com.codecool.paypal;

import com.codecool.paypal.basetypes.BaseJSONType;
import com.codecool.paypal.helper.JSONVerifier;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class TransactionList implements BaseJSONType {
    private LinkedList<Transaction> transactionList;

    public LinkedList<Transaction> getItemList() {
        return transactionList;
    }

    @Override
    public JSONAware toJSON() {
        return JSONVerifier.verifyArray(new JSONArray() {{
            for (Transaction transaction : transactionList) {
                add(transaction.toJSON());
            }
        }});
    }

    public TransactionList() {
        transactionList = new LinkedList<Transaction>();
    }

    public TransactionList(LinkedList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
