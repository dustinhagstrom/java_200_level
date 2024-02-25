
public class Clock {
	private int hour; // Hour of the day 1-12
	private int minute; // Minutes of the hour, 0-59
	private int second; // Seconds of the minute, 0-59
	private boolean isAM;
	private final int SECS_IN_MIN = 60;
	private final int MINS_IN_HR = 60;
	private final int HRS_IN_HALF_DAY = 12;

	public Clock() {
		this.setHour(12);
		this.setMinute(0);
		this.setSecond(0);
		this.setIsAM(true);
	}

	public Clock(int h, int m, int s, boolean am) {
		this.setHour(h);
		this.setMinute(m);
		this.setSecond(m);
		this.setIsAM(am);
	}

	public Clock(Clock c) {
		this.hour = c.hour;
		this.minute = c.minute;
		this.second = c.second;
		this.isAM = c.isAM;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public boolean getIsAM() {
		return isAM;
	}

	public boolean equals(Clock c) {
		if ((this.hour == c.hour)
				&& (this.minute == c.minute)
				&& (this.second == c.second)
				&& (this.isAM == c.isAM)) {
			return true;
		} else {
			return false;
		}
	}

	public void setHour(int h) {
		if ((h >= 1) && (h <= HRS_IN_HALF_DAY)) {
			hour = h;
		} else {
			throw new IllegalArgumentException("Attempt to set hour out of valid range.");
		}
	}

	public void setMinute(int m) {
		if ((m >= 0) && (m <= MINS_IN_HR)) {
			minute = m;
		} else {
			throw new IllegalArgumentException("Attempt to set minute out of valid range.");
		}
	}

	public void setSecond(int s) {
		if ((s >= 0) && (s <= MINS_IN_HR)) {
			minute = s;
		} else {
			throw new IllegalArgumentException("Attempt to set second out of valid range.");
		}
	}

	public void setIsAM(Boolean b) {
		isAM = b;
	}

	public void tick() {
		// Implement from seconds on up.
		setSecond((getSecond() + 1) % SECS_IN_MIN);

		if (getSecond() == 0) {
			// We flipped to a new minute.
			setMinute((getMinute() + 1) % MINS_IN_HR);

			if (getMinute() == 0) {
				// We flipped to a new hour.
				setHour((getHour() + 1) % HRS_IN_HALF_DAY);

				if (hour == 0) {
					// We flipped AM/PM
					setIsAM(!getIsAM());
				}
			}
		}
	}
}
