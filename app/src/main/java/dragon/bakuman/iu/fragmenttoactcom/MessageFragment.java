package dragon.bakuman.iu.fragmenttoactcom;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private EditText mEditText;
    private Button mButton;

    //callback for the interface
    OnMessageReadListener mMessageReadListener;


    public MessageFragment() {
        // Required empty public constructor
    }

    //to establish communication b/w fragment and activity, we need to specify interfaces.

    public interface OnMessageReadListener {

        void onMessageRead(String message);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        mEditText = view.findViewById(R.id.text_message);
        mButton = view.findViewById(R.id.bn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when we enter a message and press the send button, we have to call that method by using the callback interface

                String message = mEditText.getText().toString();
                mMessageReadListener.onMessageRead(message);

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        //check whether Activity implemented or not!

        try {
            mMessageReadListener = (OnMessageReadListener) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString() + "Must override OnMessageReadListener");



        }

    }
}
