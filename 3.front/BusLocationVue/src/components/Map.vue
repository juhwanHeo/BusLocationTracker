<template>
    <div>
    <div id="map"></div>
    <div class="button-group">
        <button @click="changeSize(0)">Hide</button>
        <button @click="changeSize(600)">show</button>
        <button @click="displayInfoWindow">infowindow</button>
    </div>
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
            markers: [],
            infowindow: null,
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
        this.getStations();
    },
    watch: {
        stationPosition(position) {
            if(position) this.displayMarker();
        }
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
        changeSize(size) {
            const container = document.getElementById("map");
            container.style.width = `${size}%`;
            container.style.height = `${size}px`;
            this.map.relayout();
        },
        displayMarker() {
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }

            // alert(JSON.stringify(this.stationPosition));
            const positions = this.stationPosition.map(
                (position) => new kakao.maps.LatLng(position.lat, position.lon)
            );

            if (positions.length > 0) {
                this.markers = positions.map(
                    (position) =>
                    new kakao.maps.Marker({
                        map: this.map,
                        position,
                    })
                );

                const bounds = positions.reduce(
                    (bounds, latlng) => bounds.extend(latlng),
                    new kakao.maps.LatLngBounds()
                );

                this.map.setBounds(bounds);
            }
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
            axios.get('http://localhost:3003/api/stations/')
                .then((result) => {
                    console.log(result)
                    this.stationPosition = result.data.data
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

button {
    border-radius: 10px;
    border: 2px solid black;
    padding: 10px;
    margin: 0 3px;
}
</style>
