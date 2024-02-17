import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Generally used utilities.
 */
public class Common {

    public static final String VERSION = "1.2-2024-02-26"; //version set for program submission date

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d yyyy");
	private static final DateTimeFormatter DATE_NO_YEAR_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d");
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d yyyy h:mma");
	private static final DateTimeFormatter DATE_TIME_NO_YEAR_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d h:mma");

	private static final String ASSETS_FOLDER = "assets";

	/**
	 * Formats a date and time.
	 *
	 * @param dateTime The date and time to format
	 * @return The formatted date, and time if applicable
	 */
	public static String formatDateTime(long dateTime) {

		String formattedDateTime;

		LocalDateTime localDate = new Date(dateTime)
				.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		boolean includeTime = !(localDate.getHour() == 0 && localDate.getMinute() == 0);

		if (localDate.getYear() == LocalDate.now().getYear()) {
			formattedDateTime = localDate.format(includeTime ? DATE_TIME_NO_YEAR_FORMAT : DATE_NO_YEAR_FORMAT);
		} else {
			formattedDateTime = localDate.format(includeTime ? DATE_TIME_FORMAT : DATE_FORMAT);
		}

		return formattedDateTime;
	}

	/**
	 * Gets an image from the assets folder and wraps it inside a JLabel.
	 *
	 * @param name The file name inside the assets folder
	 * @return The image, wrapped in a JLabel
	 */
	public static JLabel getImage(String name) {
		try {
			return new JLabel(
					new ImageIcon(ImageIO.read(new File(ASSETS_FOLDER + File.separator + name))));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Failed to read image: " + name);
			return null;
		}
	}

	public static Credentials hash(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException{
		System.out.println("hashing fr");
		System.out.println(String.valueOf(salt) + " before hash");

		if (salt == null){
			SecureRandom random = new SecureRandom();
			salt = new byte[16];
			random.nextBytes(salt);
		}

		System.out.println(String.valueOf(salt) + " after hash");
		System.out.println(password);

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		byte[] hash = factory.generateSecret(spec).getEncoded();
		String hashedPassword = String.format("%x", new BigInteger(hash));

		Credentials credentials = new Credentials(hashedPassword, salt);
		System.out.println(credentials.getPassword() + " " + String.valueOf(credentials.getSalt()));
		
		return credentials;
	}
}