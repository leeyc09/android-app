package jcotter.listenmoe.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private int id;
    private String artist;
    private String title;
    private String anime;
    private Boolean enabled;
    private int favorite;

    public Song(int id, String artist, String title, String anime) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.anime = anime;
        this.enabled = false;
        this.favorite = 0;
    }

    public Song(Parcel in) {
        this.id = in.readInt();
        this.artist = in.readString();
        this.title = in.readString();
        this.anime = in.readString();
        this.enabled = in.readByte() != 0;
        this.favorite = in.readInt();
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAnime() {
        return anime;
    }

    public boolean isEnabled() {
        return enabled == null;
    }

    public boolean isFavorite() {
        return favorite == 1;
    }

    // The API is inconsistent in how they flag a song as favorited...
    public void setFavorite(boolean favorite) {
        this.favorite = favorite ? 1 : 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s - %s", artist, title));

        if (anime != null && !anime.equals("")) {
            builder.append(String.format(" [ %s ]", anime));
        }

        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(artist);
        parcel.writeString(title);
        parcel.writeString(anime);
        parcel.writeByte((byte) (enabled ? 1 : 0));
        parcel.writeInt(favorite);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}