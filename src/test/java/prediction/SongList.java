package prediction;

public class SongList {
    private String listId;
    private String listName;
    private String[] labels;
    private String[] songs;

    public SongList() {
    }

    public SongList(String listId, String[] labels) {
        this.listId = listId;
        this.labels = labels;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String[] getSongs() {
        return songs;
    }

    public void setSongs(String[] songs) {
        this.songs = songs;
    }
}
