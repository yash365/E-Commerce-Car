package com.example.ecommercecar.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecommercecar.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ContactBottomSheetDialog extends BottomSheetDialogFragment {

    EditText nameTextValue, msgTextValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        nameTextValue = v.findViewById(R.id.nameTextValue);
        msgTextValue = v.findViewById(R.id.msgTextValue);
        Button sendButton = v.findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                dismiss();
            }
        });

        return v;
    }

    private void sendMail(){
        String recipientList = "developer@aigen.tech";
        String[] recipients = recipientList.split(",");

        String subject = nameTextValue.getText().toString();
        String message = msgTextValue.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");       // to send to email client
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
