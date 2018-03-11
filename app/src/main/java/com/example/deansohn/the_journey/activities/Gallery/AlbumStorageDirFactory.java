package com.example.deansohn.the_journey.activities.Gallery;

/**
 * Created by DESOHN on 11/03/2018.
 */

import java.io.File;

abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}
