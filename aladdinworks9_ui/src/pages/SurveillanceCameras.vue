<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <surveillanceCamera-table
            v-if="surveillanceCameras && surveillanceCameras.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:surveillanceCameras="surveillanceCameras"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-surveillance-cameras="getAllSurveillanceCameras"
             >

            </surveillanceCamera-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SurveillanceCameraTable from "@/components/SurveillanceCameraTable";
import SurveillanceCameraService from "../services/SurveillanceCameraService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SurveillanceCameraTable,
  },
  data() {
    return {
      surveillanceCameras: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllSurveillanceCameras(sortBy='surveillanceCameraId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SurveillanceCameraService.getAllSurveillanceCameras(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.surveillanceCameras.length) {
					this.surveillanceCameras = response.data.surveillanceCameras;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching surveillanceCameras:", error);
        }
        
      } catch (error) {
        console.error("Error fetching surveillanceCamera details:", error);
      }
    },
  },
  mounted() {
    this.getAllSurveillanceCameras();
  },
  created() {
    this.$root.$on('searchQueryForSurveillanceCamerasChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSurveillanceCameras();
    })
  }
};
</script>
<style></style>
