class Song implements Comparable<Song>{
	String title;
	String artist;
	String rating;
	String bpm;

	public boolean equals(Object aSong){
		Song s = (Song) aSong;
		return this.getTitle().equals(s.getTitle());
	}

	public int  hashCode(){
		return this.title.hashCode();
	}

	public int compareTo(Song s){
		return title.compareTo(s.getTitle());
	}

	public Song(String t, String a, String r, String b){
		this.title = t;
		this.artist = a;
		this.rating = r;
		this.bpm = b;
	}

	public String getTitle(){
		return title;
	}

	public String getArtist(){
		return artist;
	}

	public String getRating(){
		return rating;
	}

	public String getBpm(){
		return bpm;
	}

	public String toString(){
		// return ("title " + title + "/artist " + artist +"\n");
		return (title);
	}
}