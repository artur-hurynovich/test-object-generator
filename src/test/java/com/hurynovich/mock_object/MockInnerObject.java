package com.hurynovich.mock_object;

public class MockInnerObject {

	private String text;

	private String ignoredText;

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public String getIgnoredText() {
		return ignoredText;
	}

	public void setIgnoredText(final String ignoredText) {
		this.ignoredText = ignoredText;
	}

}
