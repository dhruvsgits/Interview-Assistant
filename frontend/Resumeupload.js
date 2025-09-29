import React, { useState } from "react";
import axios from "axios";

export default function ResumeUpload({ onCandidateParsed }) {
  const [file, setFile] = useState(null);

  const upload = async () => {
    const formData = new FormData();
    formData.append("file", file);
    const res = await axios.post("/resume/upload", formData);
    onCandidateParsed(res.data);
  };

  return (
    <div>
      <input type="file" onChange={(e) => setFile(e.target.files[0])} />
      <button onClick={upload}>Upload Resume</button>
    </div>
  );
}