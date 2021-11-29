<template>
  <v-container
    id="google-maps-view"
    fluid
    tag="section"
  >
    <v-row>
      <v-col
        v-for="(item, i) in maps"
        :key="i"
        :md="i !== 0 ? 6 : undefined"
        cols="12"
      >
        <material-card
          :heading="item.heading"
          color="accent"
        >
          <v-card-text>
            <v-sheet>

            <Map/>
            </v-sheet>

          </v-card-text>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import Map from './../components/Map.vue'

  export default {
  components: { Map },
    name: 'MapsView',

    data: () => ({
      maps: [
        {
          heading: 'Station Map',
        },
      ],
    }),
  }
</script>
