package MainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateUsers {
public static void main(String[] args) throws IOException{
		
		int counter, nou;

		Secretaries sec = new Secretaries();
		Professors pro = new Professors();

		FileWriter outputStream = null;
		FileWriter outputStream2 = null;

		BufferedReader inputStream = null;
		BufferedReader inputStream2 = null;

		File f = new File("src/Students.txt");
		File f2 = new File("src/UsersCounter.txt");

		try {
			inputStream2 = new BufferedReader(new InputStreamReader(new FileInputStream(f2)));

			String line2 = inputStream2.readLine();
			String[] array = line2.split("\\s+");
			nou = Integer.parseInt(array[0]);

		} finally {
			if (inputStream2 != null) {
				inputStream2.close();
			}
		}

		Users u = new Users();
		Students s = new Students();
		Courses c = new Courses();

		System.out.println("~~~~~~~~~~~~~~~~~~~~ΚΑΛΩΣ ΟΡΙΣΑΤΕ~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("----Για άνοιγμα του μενού επιλογών πατήστε το ¨1¨.----");
		System.out.println("----Για άνοιγμα του αρχείου φοιτητών πατήστε το ¨2¨.----");

		Scanner input1 = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		Scanner input4 = new Scanner(System.in);
		int ap1 = input1.nextInt();
		if (ap1 == 1) {

			int ap3 = 1;

			while (ap3 == 1) {
				Scanner input3 = new Scanner(System.in);
				System.out.println("ΓΡΑΜΜΑΤΕΙΑ");
				System.out.println("----Για εισαγωγή φοιτητή στο αρχείο πατήστε το ¨1¨.----");
				System.out.println("----Για εισαγωγή καθηγητή στο αρχείο πατήστε το ¨2¨.----");
				System.out.println("----Για εισαγωγή μαθήματος στο αρχείο πατήστε το ¨3¨.----");
				System.out.println("----Για την δημιουργία λίστας φοιτητών για το Χ μάθημα πάτηστε το ¨4¨.----");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("ΦΟΙΤΗΤΕΣ");
				System.out.println("----Για να δείτε τις βαθμολογίες σας,πατήστε το ¨5¨----");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("ΚΑΘΗΓΗΤΕΣ");
				System.out.println("----Για να βάλετε βαθμολογίες στους φοιτητές,πατήστε το ¨6¨----");
				System.out.println("----Για να δείτε τις βαθμολογίες όλων των φοιτητών σας,πατήστε το ¨7¨----");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				int ap2 = input2.nextInt();

				try {
					if (ap2 == 1) {

						System.out.println("Ονομα φοιτητή:");
						String n = input3.nextLine();
						System.out.println("Επίθετο φοιτητή:");
						String e = input3.nextLine();
						System.out.println("Ονομα χρήστη φοιτητή:");
						String us = input3.nextLine();
						System.out.println("Τμήμα φοιτητή:");
						String d = input3.nextLine();

						try {
							try {
								outputStream = new FileWriter("src/Students.txt", true);
								System.out.println("Αριθμός μητρώου φοιτητή:");
								int am = input3.nextInt();

								sec.SetStudent(n, e, us, d, am);
							} catch (InputMismatchException ex) {
								System.out.println(
										"ERROR:---->Ο αριθμός μητρώου πρέπει να αποτελείται από αριθμούς.<-----");
								continue;
							}

							counter = sec.sd.getUsercounter() + nou;

							outputStream2 = new FileWriter("src/UsersCounter.txt");
							outputStream2.write(String.valueOf(counter));
							outputStream.write("\n" + sec.sd.getName() + "		" + sec.sd.getSurname() + "		"
									+ sec.sd.getUsername() + "		" + sec.sd.getDepName() + "		"
									+ s.getRegistrationNumber());

						} finally {
							if (outputStream != null) {
								outputStream.close();
							}
						}
					}

					if (ap2 == 2) {

						System.out.println("Ονομα καθηγητή:");
						String n = input3.nextLine();
						System.out.println("Επίθετο καθηγητή:");
						String e = input3.nextLine();
						System.out.println("Ονομα χρήστη καθηγητή:");
						String us = input3.nextLine();
						System.out.println("Τμήμα καθηγητή:");
						String d = input3.nextLine();
						System.out.println("ΑΦΜ καθηγητή:");
						String afm = input3.nextLine();

						outputStream2 = new FileWriter("src/UsersCounter.txt");
						counter = sec.sd.getUsercounter() + nou;
						outputStream2.write(String.valueOf(counter));
						sec.SetProfessor(n, e, us, d, afm);

						System.out.println(sec.sd.getName() + "		" + sec.sd.getSurname() + "		"
								+ sec.sd.getUsername() + "		" + sec.sd.getDepName() + "		" + sec.p.getafm());
					}
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
					if (outputStream2 != null) {
						outputStream2.close();
					}
				}

				if (ap2 == 3) {
					System.out.println("Τίτλος μαθήματος:");
					String t = input3.nextLine();
					System.out.println("Εξάμηνο μαθήματος:");
					String x = input3.nextLine();
					System.out.println("Καθηγητής μαθήματος:");
					String k = input3.nextLine();
					System.out.println("Τμήμα διδασκόμενου μαθήματος:");
					String d = input3.nextLine();

					sec.SetCourses(t, x, k, d);
					System.out.println(sec.c.getTitle() + "		" + sec.c.getCourseSem() + "		"
							+ sec.c.getProfessor() + "		" + sec.c.getCourseDeptname());

				}

				if (ap2 == 4) {

					sec.CreateGradeList();

				}

				if (ap2 == 5) {
					s.ShowMyGrades();
				}

				if (ap2 == 6) {
					pro.SetStudentsGrades();
				}

				if (ap2 == 7) {
					pro.ShowStudentsGrades();
				}

				System.out.println("~~~~~~Για επιπλέον εγγραφή πατήστε το ¨1¨~~~~~~");
				System.out.println("~~~~~~Για έξοδο και αποθήκευση από το πρόγραμμα πατήστε το ¨2¨~~~~~~");

				ap3 = input4.nextInt();
				if (ap3 == 2) {
					for (int i = 0; i < 20; ++i)
						System.out.println();
					System.out.println("Το πρόγραμμα τερματίστηκε!");
				}

			}
		} else if (ap1 == 2) {
			try {
				inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

				inputStream.readLine();
				inputStream.readLine();
				String line = null;
				while ((line = inputStream.readLine()) != null) {
					String[] array = line.split("\\s+");
					String n = array[0];
					String e = array[1];
					String us = array[2];
					String d = array[3];
					int am = Integer.parseInt(array[4]);
					sec.SetStudent(n, e, us, d, am);
					System.out.println(sec.sd.getName() + "		" + sec.sd.getSurname() + "		" + sec.sd.getUsername()
							+ "		" + sec.sd.getDepName() + "		" + s.getRegistrationNumber());

				}
			} finally {
				if (inputStream != null) {
					inputStream.close();

				}

			}

		}
	}
}
