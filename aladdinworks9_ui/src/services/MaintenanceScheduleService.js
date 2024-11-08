import http from "../http-common"; 

class MaintenanceScheduleService {
  getAllMaintenanceSchedules(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/maintenanceSchedule/maintenanceSchedules`, searchDTO);
  }

  get(maintenanceScheduleId) {
    return this.getRequest(`/maintenanceSchedule/${maintenanceScheduleId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/maintenanceSchedule?field=${matchData}`, null);
  }

  addMaintenanceSchedule(data) {
    return http.post("/maintenanceSchedule/addMaintenanceSchedule", data);
  }

  update(data) {
  	return http.post("/maintenanceSchedule/updateMaintenanceSchedule", data);
  }
  
  uploadImage(data,maintenanceScheduleId) {
  	return http.postForm("/maintenanceSchedule/uploadImage/"+maintenanceScheduleId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new MaintenanceScheduleService();
