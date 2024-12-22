package enums;

public enum DayOfWeek {

	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY;

	/**
	 * Возвращает следующий день недели.
	 *
	 * @return следующий день недели.
	 */
	public DayOfWeek next() {
		int nextOrdinal = (this.ordinal() + 1) % values().length;
		return values()[nextOrdinal];
	}

	/**
	 * Проверяет, является ли день выходным.
	 *
	 * @return true, если день выходной, иначе false.
	 */
	public boolean isWeekend() {
		return this == SATURDAY || this == SUNDAY;
	}

	/**
	 * Преобразует строку в соответствующий день недели.
	 *
	 * @param day Строковое представление дня недели.
	 * @return соответствующий день недели.
	 * @throws IllegalArgumentException если строка не соответствует ни одному дню недели.
	 */
	public static DayOfWeek fromString(String day) {
		switch (day.toLowerCase()) {
			case "monday":
				return MONDAY;
			case "tuesday":
				return TUESDAY;
			case "wednesday":
				return WEDNESDAY;
			case "thursday":
				return THURSDAY;
			case "friday":
				return FRIDAY;
			case "saturday":
				return SATURDAY;
			case "sunday":
				return SUNDAY;
			default:
				throw new IllegalArgumentException("Неверное название дня недели: " + day);
		}
	}
}

