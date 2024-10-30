package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("Error no filename provided");
			System.exit(1);
		}

		String filename = args[0];
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File does not exist");
		}
		Reader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);

		List<BikeEntry> lbe = new ArrayList<>();
		String input;
		int count = 0;
		while ((input = br.readLine()) != null) {
			if (count == 0) {
				count++;
				continue;
			}

			String[] data = input.split(",");
			lbe.add(BikeEntry.toBikeEntry(data));
		}
		List<BikeEntry> result = lbe.stream().sorted(Comparator.comparing(BikeEntry::getRegistered).reversed())
				.toList();
		for (int i = 0; i < 5; i++) {
			BikeEntry holder = result.get(i);
			String position = "";
			if (i == 0) {
				position = "highest";
			} else if (i == 1) {
				position = "second highest";

			} else if (i == 2) {
				position = "third highest";
			} else if (i == 3) {
				position = "fourth highest";
			} else if (i == 4) {
				position = "fifth highest";
			}

			String weather = "";
			if(holder.getWeather() == 1) {
				weather = "Clear, Few clouds, Partly cloudy, Partly cloudy";
			}
			if(holder.getWeather() == 2) {
				weather = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
			}
			if(holder.getWeather() == 3) {
				weather = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
			}
			if(holder.getWeather() == 4) {
				weather = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
			}

			System.out.printf("The %s recorded number of cyclists was in %s, on a %s in the month of %s.\n", position,
					Utilities.toSeason(holder.getSeason()), Utilities.toWeekday(holder.getWeekday()),
					Utilities.toMonth(holder.getMonth()));
			System.out.printf("There was a total of %d cyclist. The weather was %s.\n", holder.getRegistered(),
					weather);
			if (result.get(i).isHoliday()) {
				System.out.printf("%s was not a holiday.\n", Utilities.toWeekday(holder.getWeekday()));
			} else {
				System.out.printf("%s was a holiday.\n", Utilities.toWeekday(holder.getWeekday()));
			}
			System.out.println("");
		}

	}
}
