import java.io.*;
import java.util.*;

public class NaiveBayesClassifier {

	public static HashMap < Integer, Float > classProb = new HashMap < Integer, Float > (); //Map to hold all the class values and sum of 	

	public static HashMap < Integer, HashMap < Integer, Float >> meanMatrix = new HashMap < Integer, HashMap < Integer, Float >> (); //Matrix to hold the mean values
	public static HashMap < Integer, HashMap < Integer, Float >> varianceMatrix = new HashMap < Integer, HashMap < Integer, Float >> (); //Matrix to hold variance values

	public static List < List < Float >> trainList = new ArrayList < List < Float >> (); //List to hold the training data values


	public static List < List < Float >> testList = new ArrayList < List < Float >> (); //List to hold the test data
	public static HashMap < Integer, Integer > classCount = new HashMap < Integer, Integer > ();
	public static void main(String args[]) throws Exception {

		FileWriter fstream = new FileWriter("output.txt");

		BufferedWriter out = new BufferedWriter(fstream);

		calcPrior(out); //calling method to calculate prior probability of classes
		buildMatrix(out); //Calling the method to build the conditional mean and variance matrix
		predict(out); //Calling the method to make predictions for the test data
		calcAccuracy(out);//calling the method to calculate accuracy

		out.close();

	}
	
	


	/*
	 * Method to calculate the prior probability of classes
	 */
	public static void calcPrior(BufferedWriter out) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("car.csv")); //Opening training data for reading


		String vector = br.readLine();

		int counter = 0;

		while (vector != null) {

			String[] trainData = vector.split(","); //Splitting the line by space

			List < Float > tempList = new ArrayList < Float > ();

			/*Converting the text file to List of Lists */
			for (int i = 0; i < trainData.length; i++) {
				tempList.add(Float.parseFloat(trainData[i]));
			}

			int key = Math.round(tempList.get(4));

			/* calculating class probability */
			if (classProb.containsKey(key)) {
				float count = classProb.get(key);
				classProb.put(key, count + 1);
				classCount.put(key, classCount.get(key) + 1);

			} else {
				classProb.put(key, (float) 1);
				classCount.put(key, 1);
			}

			trainList.add(tempList); //Adding the temporary List to main list of train data


			vector = br.readLine();
			counter++;




		}


		out.write("Prior probabilities of classes");
		out.write("\n\n");

		for (int s: classProb.keySet()) {

			classProb.put(s, classProb.get(s) / counter);

			System.out.println(classCount.get(s));

			out.write(classProb.get(s).toString());
			out.write(" ");


			System.out.println(s + " " + classProb.get(s)); //printing the probability of each class
		}
		br.close();



	}

	/*
	 * Method to build the mean and variance matrices
	 */

	public static void buildMatrix(BufferedWriter out) throws IOException {


		/* Counters for class occurrences */

		for (int k: classCount.keySet()) {

			for (int i = 0; i < trainList.size(); i++) {
				if (k == Math.round(trainList.get(i).get(4))) {

					if (meanMatrix.containsKey(k)) {

						HashMap < Integer, Float > temp = new HashMap < Integer, Float > ();
						temp = meanMatrix.get(k);

						temp.put(0, temp.get(0) + trainList.get(i).get(0));
						temp.put(1, temp.get(1) + trainList.get(i).get(1));
						temp.put(2, temp.get(2) + trainList.get(i).get(2));
						temp.put(3, temp.get(3) + trainList.get(i).get(3));

						meanMatrix.put(k, temp); //Adding up the values to find mean

					} else {

						HashMap < Integer, Float > temp = new HashMap < Integer, Float > ();

						temp.put(0, trainList.get(i).get(0));
						temp.put(1, trainList.get(i).get(1));
						temp.put(2, trainList.get(i).get(2));
						temp.put(3, trainList.get(i).get(3));
						meanMatrix.put(k, temp);
					}

				}

			}
		}

		out.write("\n\n");
		out.write("mean matrix");
		out.write("\n");

		for (int s: meanMatrix.keySet()) { //calculating the mean

			HashMap < Integer, Float > temp = meanMatrix.get(s);

			temp.put(0, temp.get(0) / classCount.get(s));
			temp.put(1, temp.get(1) / classCount.get(s));
			temp.put(2, temp.get(2) / classCount.get(s));
			temp.put(3, temp.get(3) / classCount.get(s));


			meanMatrix.put(s, temp);
			System.out.println(temp.get(0));
			System.out.println(temp.get(1));
			System.out.println(temp.get(2));
			System.out.println(temp.get(3));

			out.write(temp.get(0).toString());
			out.write(" ");
			out.write(temp.get(1).toString());
			out.write(" ");
			out.write(temp.get(2).toString());
			out.write(" ");
			out.write(temp.get(3).toString());
			out.write("\n");

		}



		for (int c: classCount.keySet()) {

			for (int i = 0; i < trainList.size(); i++) {
				if (c == Math.round(trainList.get(i).get(4))) {

					if (varianceMatrix.containsKey(c)) {

						HashMap < Integer, Float > temp = new HashMap < Integer, Float > ();
						temp = varianceMatrix.get(c);

						temp.put(0, temp.get(0) + sqr(trainList.get(i).get(0) - meanMatrix.get(c).get(0)));
						temp.put(1, temp.get(1) + sqr(trainList.get(i).get(1) - meanMatrix.get(c).get(1)));
						temp.put(2, temp.get(2) + sqr(trainList.get(i).get(2) - meanMatrix.get(c).get(2)));
						temp.put(3, temp.get(3) + sqr(trainList.get(i).get(3) - meanMatrix.get(c).get(3)));

						varianceMatrix.put(c, temp); //Adding up the values to find variance

					} else {

						HashMap < Integer, Float > temp = new HashMap < Integer, Float > ();

						temp.put(0, sqr(trainList.get(i).get(0) - meanMatrix.get(c).get(0)));
						temp.put(1, sqr(trainList.get(i).get(1) - meanMatrix.get(c).get(1)));
						temp.put(2, sqr(trainList.get(i).get(2) - meanMatrix.get(c).get(2)));
						temp.put(3, sqr(trainList.get(i).get(3) - meanMatrix.get(c).get(3)));
						varianceMatrix.put(c, temp);
					}

				}

			}
		}

		out.write("\n");
		out.write("\n\n");
		out.write("variance matrix");
		out.write("\n");


		for (int s: varianceMatrix.keySet()) { //calculating the variance

			HashMap < Integer, Float > temp = varianceMatrix.get(s);

			temp.put(0, temp.get(0) / classCount.get(s));
			temp.put(1, temp.get(1) / classCount.get(s));
			temp.put(2, temp.get(2) / classCount.get(s));
			temp.put(3, temp.get(3) / classCount.get(s));


			varianceMatrix.put(s, temp);
			System.out.println(temp.get(0));
			System.out.println(temp.get(1));
			System.out.println(temp.get(2));
			System.out.println(temp.get(3));

			out.write(temp.get(0).toString());
			out.write(" ");
			out.write(temp.get(1).toString());
			out.write(" ");
			out.write(temp.get(2).toString());
			out.write(" ");
			out.write(temp.get(3).toString());
			out.write("\n");

		}


	}

	public static void predict(BufferedWriter out) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("car.csv"));
		String vector = br.readLine();
		
		FileWriter fstream = new FileWriter("predictions.txt");

		BufferedWriter out1 = new BufferedWriter(fstream);

		while (vector != null) {
			String[] testData = vector.split("\\s+"); //Splitting the line by space

			List < Float > tempList = new ArrayList < Float > ();

			/*Converting the text file to List of Lists */
			for (int i = 0; i < testData.length; i++) {
				tempList.add(Float.parseFloat(testData[i]));
			}

			testList.add(tempList); //Adding the temporary List to main list of train data


			vector = br.readLine();
		}

		out.write("\n\n");
		out.write("predictions");
		out.write("\n");


		for (int i = 0; i < testList.size(); i++) {

			HashMap < Integer, Float > posterior = new HashMap < Integer, Float > ();

			for (int j = 0; j < testList.get(i).size() - 1; j++) {
				out.write(testList.get(i).get(j).toString());
				out1.write(testList.get(i).get(j).toString());
				out.write(" ");
				out1.write(" ");

			}

			out.write(Integer.toString(Math.round(testList.get(i).get(4))));
			out.write(" ");
			
			out1.write(Integer.toString(Math.round(testList.get(i).get(4))));
			out1.write(" ");



			for (int s: meanMatrix.keySet()) {
				HashMap < Integer, Float > gaussians = new HashMap < Integer, Float > ();
				for (int k = 0; k < testList.get(i).size() - 1; k++) {
					gaussians.put(k, gaussian(testList.get(i).get(k), s, k));

				}

				posterior.put(s, calcPosterior(gaussians, s));


			}

			Map.Entry < Integer, Float > maxEntry = null;

			for (Map.Entry < Integer, Float > entry: posterior.entrySet()) {
				if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = entry;
				}
			}

			//Write results to file

			out.write(maxEntry.getKey().toString());
			out.write("\n");
			
			out1.write(maxEntry.getKey().toString());
			out1.write("\n");

		}
		
		out1.close();
	}

	public static float gaussian(Float x, int label, int feature) throws IOException {

		float conditionalMean = meanMatrix.get(label).get(feature);
		float conditionalVariance = varianceMatrix.get(label).get(feature);


		return (float)(Math.exp(-1 * (sqr(x - conditionalMean) / (2 * conditionalVariance)))) / (float)(Math.sqrt(2 * Math.PI * conditionalVariance));

	}

	public static float calcPosterior(Map < Integer, Float > gaussians, int label) throws IOException {

		float result = 1;

		for (int s: gaussians.keySet()) {
			result = result * gaussians.get(s);

		}

		result = result * classProb.get(label);

		return result;
	}

	public static void calcAccuracy(BufferedWriter out) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("predictions.txt"));

		int rightPredictions = 0;


		String vector = br.readLine();

		while (vector != null) {

			String[] predictions = vector.split(","); //Splitting the line by space

			int prediction = Integer.parseInt(predictions[predictions.length - 1]);
			int label = Integer.parseInt(predictions[predictions.length - 2]);

			if (prediction == label) {
				rightPredictions = rightPredictions + 1;
			}
			vector = br.readLine();
		}
		System.out.println(rightPredictions);
		System.out.println(testList.size());
		out.write("\n\n");
		out.write("Accuracy");

		out.write(Float.toString(((float) rightPredictions / testList.size()) * 100));
		System.out.println("Accuracy:" + ((float) rightPredictions / testList.size()) * 100);

		br.close();

	}
	
	/*
	 * Method to calculate square 
	 */
	public static float sqr(float num) throws IOException {
		return num * num;
	}
}