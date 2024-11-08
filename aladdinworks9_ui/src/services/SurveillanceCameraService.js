import http from "../http-common"; 

class SurveillanceCameraService {
  getAllSurveillanceCameras(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/surveillanceCamera/surveillanceCameras`, searchDTO);
  }

  get(surveillanceCameraId) {
    return this.getRequest(`/surveillanceCamera/${surveillanceCameraId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/surveillanceCamera?field=${matchData}`, null);
  }

  addSurveillanceCamera(data) {
    return http.post("/surveillanceCamera/addSurveillanceCamera", data);
  }

  update(data) {
  	return http.post("/surveillanceCamera/updateSurveillanceCamera", data);
  }
  
  uploadImage(data,surveillanceCameraId) {
  	return http.postForm("/surveillanceCamera/uploadImage/"+surveillanceCameraId, data);
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

export default new SurveillanceCameraService();
