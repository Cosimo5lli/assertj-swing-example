package com.example.assertj.swing;

import javax.swing.JButton;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FrameTest {
	private FrameFixture frameFixture;

	@BeforeClass
	public static void onceSetUp() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setup() {
		Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
		robot.settings().delayBetweenEvents(100);
		ExampleJFrame frame = GuiActionRunner.execute(() -> new ExampleJFrame());
		frameFixture = new FrameFixture(robot, frame);
		frameFixture.show();
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButton() {
		frameFixture.textBox("textToCopy").deleteText();
		frameFixture.textBox("textToCopy").enterText("Hello!");
		frameFixture.button("copyButton").click();
		frameFixture.label("copiedText").requireText("Hello!");
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButtonWithGenericTypeMatcher() {
		frameFixture.textBox("textToCopy").deleteText();
		frameFixture.textBox("textToCopy").enterText("Hello!");
		GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {
			@Override
			protected boolean isMatching(JButton button) {
				return "Copy".equals(button.getText());
			}
		};
		frameFixture.button(textMatcher).click();
		frameFixture.label("copiedText").requireText("Hello!");
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButtonWithComponentMatcher() {
		frameFixture.textBox("textToCopy").deleteText();
		frameFixture.textBox("textToCopy").enterText("Hello!");
		frameFixture.button(withText("Copy")).click();
		frameFixture.label("copiedText").requireText("Hello!");
	}

	@After
	public void signalSemaphoreResources() {
		frameFixture.cleanUp();
	}

}