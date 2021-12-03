<template>
    <div>
        <div id="map">
        </div>
        <v-fab-transition>
            <v-btn
                :loading=isLoding
                @click=reload()
                color="green"
                dark
                absolute
                bottom
                right
                fab
            >
                <v-icon>mdi-refresh</v-icon>
            </v-btn>
        </v-fab-transition>
        <button @click="dispalyPolyline()">polyline</button>

    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "KakaoMap",
    data() {
        return {
            map: null,
            stationPosition: null,
            busLogs: null,
            currentBus: null,
            markers: [],
            infowindow: null,
            polyline: null,
            image: require('@/assets/bus.svg'),
            loading: null,
            isLoding: true
        };
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b37ad6a5417816603a3753a79e838f06";
            document.head.appendChild(script);
        }

        this.loading = setInterval(this.init, 1000);
    },
    methods: {
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(37.660935, 127.32249),
                level: 5,
            };
            this.map = new kakao.maps.Map(container, options);
        },
        init() {
            if (
                this.stationPosition !== null
                && this.currentBus !== null
                && this.busLogs != null
                ) {
                clearInterval(this.loading);
                this.displayMarker();
                this.dispalyPolyline();

                this.isLoding=false;

            }
            else {
                this.getStations();
                this.getCurrentBus();
                this.getBusLogs();

                this.isLoding=true;
            }
        },
        reload() {
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }

            this.polyline.setMap(null);
            this.isLoding = true;
            setTimeout(() => {
                this.getStations();
                this.getCurrentBus();
                this.getBusLogs();

                this.displayMarker();
                this.dispalyPolyline();
                this.isLoding = false;
            }, 1000)
        },
        displayMarker() {
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }
            const positions = this.stationPosition.map(
                (position) => new kakao.maps.LatLng(position.lat, position.lon)
            );

            if (positions.length > 0) {
                this.markers = positions.map(
                    (position) =>
                    new kakao.maps.Marker({
                        map: this.map,
                        position
                    })
                );

                var icon = new kakao.maps.MarkerImage(
                    this.image,
                    new kakao.maps.Size(31, 35),
                    {
                        offset: new kakao.maps.Point(16, 34),
                        alt: "마커 ex",
                    }
                );

                this.markers.push(new kakao.maps.Marker({
                        map: this.map,
                        position : new kakao.maps.LatLng(this.currentBus.lat, this.currentBus.lon),
                        image: icon
                    }));

                const bounds = positions.reduce(
                    (bounds, latlng) => bounds.extend(latlng),
                    new kakao.maps.LatLngBounds()
                );

                this.map.setBounds(bounds);
            }


        },
        dispalyPolyline() {
            const paths = this.busLogs.map(
                (position) => new kakao.maps.LatLng(position.lat, position.lon)
            );

            this.polyline = new kakao.maps.Polyline({
                map: this.map,
                endArrow: true,
                path: paths,
                strokeWeight: 2,
                strokeColor: '#F10000',
                strokeOpacity: 0.8,
                strokeStyle: 'solid'
            });
            this.polyline.setMap(this.map); // 지도에 올린다.
        },
        displayInfoWindow() {
            if (this.infowindow && this.infowindow.getMap()) {
                //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
                this.map.setCenter(this.infowindow.getPosition());
                return;
            }

            var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = new kakao.maps.LatLng(37.660935, 127.32249), //인포윈도우 표시 위치입니다
            iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

            this.infowindow = new kakao.maps.InfoWindow({
                map: this.map, // 인포윈도우가 표시될 지도
                position: iwPosition,
                content: iwContent,
                removable: iwRemoveable,
            });

            this.map.setCenter(iwPosition);
        },
        async getStations() {
            await axios.get('http://localhost:3003/api/stations/')
                .then((result) => {
                    console.log("stations: \n" + JSON.stringify(result));
                    this.stationPosition = result.data.data
                })
                .catch((err) => {
                    console.log(err)
                })
        },
        async getBusLogs() {
            await axios.get('http://localhost:3003/api/bus-logs/')
                .then((result) => {
                    console.log("bus Logs: \n" + JSON.stringify(result));
                    this.busLogs = result.data.data
                })
                .catch((err) => {
                    console.log(err)
                })
        },
        async getCurrentBus() {
            await axios.get('http://localhost:3003/api/bus-logs/current')
                .then((result) => {
                    console.log("currentBus: \n" + JSON.stringify(result));
                    this.currentBus = result.data.data
                })
                .catch((err) => {
                    console.log(err)
                })
        }
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
    width: 100%;
    height: 600px;
}

.button-group {
    margin: 10px 0px;
}


</style>
