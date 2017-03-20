package de.eosn.stackcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Stack;

public class StackCalc extends AppCompatActivity {

	private static final String TAG = StackCalc.class.getSimpleName();
	private boolean userIsTypingNumber = false;
	private View.OnClickListener numberListener, binaryOperationsListener,
			unaryOperationsListener, signListener, deleteListener, returnListener;
	private View.OnLongClickListener deleteAllListener;
	private TextView display;
	private Stack<Double> stack = new Stack<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stack_calc);

		display = (TextView) findViewById(R.id.display);
		display.setText(getText(R.string.zero));

		defineListeners();
		addListeners();
	}

	private void defineListeners() {
		defineNumberListener();
		defineBinaryOperations();
		defineSignListener();
		defineDeleteListeners();
		defineReturnListener();
		defineUnaryOperations();
	}

	private void defineNumberListener() {
		numberListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!userIsTypingNumber) {
					display.setText(""); // clear display
				}
				userIsTypingNumber = true;

				String newDisplayText = display.getText().toString();
				switch (v.getId()) {
					case R.id.button_zero:
						newDisplayText += getString(R.string.zero);
						break;
					case R.id.button_one:
						newDisplayText += getString(R.string.one);
						break;
					case R.id.button_two:
						newDisplayText += getString(R.string.two);
						break;
					case R.id.button_three:
						newDisplayText += getString(R.string.three);
						break;
					case R.id.button_four:
						newDisplayText += getString(R.string.four);
						break;
					case R.id.button_five:
						newDisplayText += getString(R.string.five);
						break;
					case R.id.button_six:
						newDisplayText += getString(R.string.six);
						break;
					case R.id.button_seven:
						newDisplayText += getString(R.string.seven);
						break;
					case R.id.button_eight:
						newDisplayText += getString(R.string.eight);
						break;
					case R.id.button_nine:
						newDisplayText += getString(R.string.nine);
						break;
					case R.id.button_decimal_point:
						newDisplayText += getString(R.string.decimal_point);
						break;
				}
				display.setText(newDisplayText);
			}
		};
	}

	private void defineBinaryOperations() {
		binaryOperationsListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!userIsTypingNumber) {
					Double op1, op2, res;
					switch (v.getId()) {
						case R.id.button_add:
							op2 = stack.pop();
							op1 = stack.pop();
							res = op1 + op2;
							display.setText(res.toString());
							stack.push(res);
							break;
						case R.id.button_sub:
							op2 = stack.pop();
							op1 = stack.pop();
							res = op1 - op2;
							display.setText(res.toString());
							stack.push(res);
							break;
						case R.id.button_mul:
							op2 = stack.pop();
							op1 = stack.pop();
							res = op1 * op2;
							display.setText(res.toString());
							stack.push(res);
							break;
						case R.id.button_div:
							op2 = stack.pop();
							op1 = stack.pop();
							res = op1 / op2;
							display.setText(res.toString());
							stack.push(res);
							break;
						case R.id.button_mod:
							op2 = stack.pop();
							op1 = stack.pop();
							res = op1 % op2;
							display.setText(res.toString());
							stack.push(res);
							break;
					}
				}
			}
		};
	}

	private void defineUnaryOperations() {
		unaryOperationsListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!userIsTypingNumber) {
					Double op, res;
					switch (v.getId()) {
						case R.id.button_sqrt:
							op = stack.pop();
							res = Math.sqrt(op);
							display.setText(res.toString());
							stack.push(res);
							break;
					}
				}
			}
		};
	}

	private void defineReturnListener() {
		returnListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (userIsTypingNumber) {
					userIsTypingNumber = false;
					try {
						String numberText = display.getText().toString();
						Double num = Double.valueOf(numberText);
						stack.push(num);
						Log.d(TAG, "onClick: num=" + num);
					} catch (NumberFormatException e) {
						Log.e(TAG, "onClick: ", e);
						display.setText(getString(R.string.error));
					}
				}
			}
		};
	}

	private void defineSignListener() {
		signListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (userIsTypingNumber) {
					String displayContent = display.getText().toString();
					if (displayContent.contains(getString(R.string.sub))) {
						displayContent = displayContent.substring(1);
					} else {
						displayContent = getString(R.string.sub) + displayContent;
					}
					display.setText(displayContent);
				}
			}
		};
	}

	private void defineDeleteListeners() {
		deleteListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (userIsTypingNumber) {
					String displayText = display.getText().toString();
					if (displayText.length() > 0) {
						displayText = displayText.substring(0, displayText.length() - 1);
						display.setText(displayText);
					} else {
						userIsTypingNumber = false;
					}
				}
			}
		};
		deleteAllListener = new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				display.setText("");
				userIsTypingNumber = false;
				return true;
			}
		};
	}

	private void addListeners() {
		addNumberListeners();
		addOperationsListener();

		findViewById(R.id.button_return).setOnClickListener(returnListener);

		findViewById(R.id.button_sign).setOnClickListener(signListener);

		findViewById(R.id.button_delete).setOnClickListener(deleteListener);
		findViewById(R.id.button_delete).setOnLongClickListener(deleteAllListener);
	}

	private void addOperationsListener() {
		findViewById(R.id.button_add).setOnClickListener(binaryOperationsListener);
		findViewById(R.id.button_sub).setOnClickListener(binaryOperationsListener);
		findViewById(R.id.button_mul).setOnClickListener(binaryOperationsListener);
		findViewById(R.id.button_div).setOnClickListener(binaryOperationsListener);
		findViewById(R.id.button_mod).setOnClickListener(binaryOperationsListener);

		findViewById(R.id.button_sqrt).setOnClickListener(unaryOperationsListener);
	}


	private void addNumberListeners() {
		findViewById(R.id.button_zero).setOnClickListener(numberListener);
		findViewById(R.id.button_one).setOnClickListener(numberListener);
		findViewById(R.id.button_two).setOnClickListener(numberListener);
		findViewById(R.id.button_three).setOnClickListener(numberListener);
		findViewById(R.id.button_four).setOnClickListener(numberListener);
		findViewById(R.id.button_five).setOnClickListener(numberListener);
		findViewById(R.id.button_six).setOnClickListener(numberListener);
		findViewById(R.id.button_seven).setOnClickListener(numberListener);
		findViewById(R.id.button_eight).setOnClickListener(numberListener);
		findViewById(R.id.button_nine).setOnClickListener(numberListener);
		findViewById(R.id.button_decimal_point).setOnClickListener(numberListener);
	}

}
