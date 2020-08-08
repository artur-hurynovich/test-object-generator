package com.hurynovich.mock_object;

public class MockUserObject {

	private boolean active;

	private MockInnerObject inner;

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public MockInnerObject getInner() {
		return inner;
	}

	public void setInner(final MockInnerObject inner) {
		this.inner = inner;
	}

}
