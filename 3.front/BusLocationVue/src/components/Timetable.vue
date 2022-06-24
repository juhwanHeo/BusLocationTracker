<template>
  <v-simple-table v-if="data !== null"
    class="bus-table">
    <template v-slot:default>
      <thead class="bus-table-header">
      <tr>
        <th v-for="header in headers">
          {{ header.text }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="timeRow in data.timeRowList"
        :key="timeRow.order"
      >
        <td>{{ timeRow.order }}</td>
        <td v-for="index in (headers.length - 1)">
          {{ getTime(index, timeRow.timeList[index - 1]) }}
        </td>
      </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<style>
.bus-table-header  {

}
.bus-table {
  color: #333333;
  font-weight: bold;
}
</style>

<script>

export default {
  name: 'Timetable',
  props: {
    headers: {
      type: Object,
      value: null
    },
    data: {
      type: Object,
      value: null
    },
    isLoading: Boolean
  },
  methods: {
    getTime(index, time) {
      if (time.station) return this.headers[index].value === time.station.stationCode ? time.time : '-';
      return '-';
    }
  }
}
</script>
