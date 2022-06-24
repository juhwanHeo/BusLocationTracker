<template>
  <v-container
    id="google-maps-view"
    fluid
    tag="section"
  >
    <v-row>
      <v-col>
        <material-card
          :heading="heading"
          color="accent"
        >
          <v-card-text>
            <v-sheet>
              <v-skeleton-loader
                v-if="isLoading"
                max-width="100%"
                type="card"
              />
              <timetable
                v-else
                :headers="headers"
                :data="data"
              />
            </v-sheet>
          </v-card-text>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import Timetable from "@/components/Timetable";
import axios from "axios";

export default {
  name: 'TimetableView',
  components: {Timetable},
  data: () => ({
    heading: 'Timetable',
    headers: [
      {text: '순서', align: 'start', value: 'order'},
      {text: 'APT 출발', value: 'S001',},
      {text: '마석역 \n1번출구', value: 'S002'},
      {text: '심석 중.고', value: 'S004'},
      {text: '송라 초.중', value: 'S005'},
      {text: '마석 초.중', value: 'S006'},
      {text: '마석고', value: 'S007'},
      {text: '마석역 \n2번출구', value: 'S003'},
      {text: 'APT 도착', value: 'S001'},
    ],
    data: null
  }),
  mounted() {
    setTimeout(this.init, 500);
  },
  computed: {
    isLoading() {
      return this.data === null;
    }
  },
  methods: {
    init() {
      this.getTimetableData();
    },
    async getTimetableHeader() {
      await axios.get('http://localhost:3003/api/timetable/header')
        .then((result) => {
          this.data = result.data;
        })
        .catch((err) => {
          console.log(err)
        })
    },
    async getTimetableData() {
      await axios.get('http://localhost:3003/api/timetable')
        .then((result) => {
          this.data = result.data;
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
}
</script>
