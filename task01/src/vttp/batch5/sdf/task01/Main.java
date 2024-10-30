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

		String filename = "task01/day.csv";
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
		br.close();
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
			String line = "The <position> recorded number of cyclists was in <season>, on a <day> in the month of <month>.\nThere was a total of <total> cyclists. The weather was <weather>.\n<day> was <holiday>.";
			line = line.replace("<position>",position + " " +"(position)");
			line = line.replace("<season>",Utilities.toSeason(holder.getSeason()) + " " +"(season)");
			line = line.replace("<day>",Utilities.toWeekday(holder.getWeekday()) + " " +"(day)");
			line = line.replace("<month>",Utilities.toMonth(holder.getMonth()) + " " +"(month)");
			line = line.replace("<total>",holder.getRegistered() + " " +"(total)");
			line = line.replace("<weather>",weather + " " + "(weather)");
			if (result.get(i).isHoliday()) {
				line = line.replace("<holiday>","a holiday");
			} else {
				line = line.replace("<holiday>","not a holiday");
			}
			System.out.println(line);
			System.out.println("");
		}

	}
}
