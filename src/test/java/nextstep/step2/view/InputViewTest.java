package nextstep.step2.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class InputViewTest {
	@Test
	@DisplayName("로또 구입금액  입력메시지 테스트")
	public void getInputMoneyTest() {
		InputView underTest = new InputView(new Scanner("14000"));
		int inputMoney = underTest.getInputMoney();
		assertThat(inputMoney).isEqualTo(14000);
	}

	@Test
	@DisplayName("로또 수동구입개수 입력테스트")
	public void inputMessageTest() {
		InputView underTest = new InputView(new Scanner("2"));
		int manualCount = underTest.getInputManualCount();
		assertThat(manualCount).isEqualTo(2);
	}

	@Test
	@DisplayName("로또 수동구입 로또번 입력테스트")
	public void getInputManualNumbersTest() {
		InputView underTest = new InputView(new Scanner("1,2,3,4,5,6\n7,8,9,10,11,12"));
		String manualNumbers = underTest.getInputManualNumbers(2);
		assertThat(manualNumbers).isEqualTo("1,2,3,4,5,6:7,8,9,10,11,12");
	}

	@Test
	@DisplayName("로또 구입금액은 숫자여야 한다")
	public void inputValidateCarNameTest() {
		InputView underTest = new InputView(new Scanner("a"));
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> underTest.getInputMoney())
				.withMessage("숫자로 입력해주세요.");
	}

	@Test
	@DisplayName("지난주 로또 입력 테스트")
	public void inputLastLottoNumberTest() {
		InputView underTest = new InputView(new Scanner("1,2,3,4,5,6"));
		String inputLastLotto = underTest.getInputLottoWinningNumbers();
		assertThat(inputLastLotto).isEqualTo("1,2,3,4,5,6");
	}

	@Test
	@DisplayName("지난주 로또 당첨번호 비어있으면 안된다.")
	public void inputWrongLastLottoTest() {
		InputView underTest = new InputView(new Scanner(""));
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> underTest.getInputLottoWinningNumbers())
				.withMessage("문자열은 필수입니다.");
	}

	@Test
	@DisplayName("보너스 번호 입력메시지 테스트")
	public void inputLottoBonusTest() {
		InputView underTest = new InputView(new Scanner("7"));
		assertThat(underTest.getInputLottoBonusNumber()).isEqualTo(7);
	}

	@Test
	@DisplayName("잘못된 보너스 번호 입력 테스트")
	public void inputWrongLottoBonusTest() {
		InputView underTest = new InputView(new Scanner("a"));
		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> underTest.getInputLottoBonusNumber())
				.withMessage("숫자로 입력해주세요.");
	}
}
