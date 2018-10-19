package huang.bling.hackathon;

public interface AppContract {
    interface MapModule<MapClass,LocationClass>{
        public MapClass getMap();
        public LocationClass getCurrentLocation();
    }

    interface WeatherModule<TsunamiInfo,LocationClass>{
        public TsunamiInfo[] getTsunamiInfo(LocationClass[] locations);
    }

    interface MainView<MapClass,LocationClass,TsunamiInfo>{
        public void updateMap(MapClass map);
        public void setLocationTsunamiInfo(LocationClass location, TsunamiInfo info);
    }

    interface ServiceView<TsunamiInfo>{
        public void setTsunamiInfo(TsunamiInfo info);
    }

    interface MainViewPresenter{
        public void mapNeedUpdate();
    }
    interface ServicePresenter{
        public void queryCurrentTsunamiInfo();
    }
}


