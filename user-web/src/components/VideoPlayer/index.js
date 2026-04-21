import { useRef, useState, useEffect } from "react";
import ReactPlayer from "react-player";
import PropTypes from "prop-types";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDProgress from "components/MDProgress";

function VideoPlayer({ url, title, onProgress, onEnded, initialProgress }) {
  const playerRef = useRef(null);
  const [played, setPlayed] = useState(0);
  const [duration, setDuration] = useState(0);
  const [isReady, setIsReady] = useState(false);

  // Handle player progress updates
  const handleProgress = (state) => {
    setPlayed(state.played * 100);
    if (onProgress) {
      onProgress(state);
    }
  };

  // Handle video duration
  const handleDuration = (duration) => {
    setDuration(duration);
  };

  // Seek to initial progress when ready
  const handleReady = () => {
    setIsReady(true);
    if (initialProgress && playerRef.current) {
      playerRef.current.seekTo(initialProgress, "fraction");
    }
  };

  return (
    <MDBox>
      <MDBox
        position="relative"
        width="100%"
        sx={{
          paddingTop: "56.25%", // 16:9 Aspect Ratio
          backgroundColor: "#000",
          borderRadius: "lg",
          overflow: "hidden",
        }}
      >
        <ReactPlayer
          ref={playerRef}
          url={url}
          width="100%"
          height="100%"
          style={{ position: "absolute", top: 0, left: 0 }}
          controls={true}
          onProgress={handleProgress}
          onDuration={handleDuration}
          onEnded={onEnded}
          onReady={handleReady}
          config={{
            file: {
              attributes: {
                controlsList: "nodownload", // Disable download
              },
            },
          }}
        />
      </MDBox>

      <MDBox mt={2}>
        <MDBox display="flex" justifyContent="space-between" alignItems="center" mb={1}>
          <MDTypography variant="h6" fontWeight="medium">
            {title}
          </MDTypography>
          <MDTypography variant="caption" color="text">
            진도율: {Math.round(played)}%
          </MDTypography>
        </MDBox>
        <MDProgress value={played} color="info" variant="gradient" label={false} />
      </MDBox>
    </MDBox>
  );
}

VideoPlayer.defaultProps = {
  initialProgress: 0,
  title: "동영상 강의",
};

VideoPlayer.propTypes = {
  url: PropTypes.string.isRequired,
  title: PropTypes.string,
  onProgress: PropTypes.func,
  onEnded: PropTypes.func,
  initialProgress: PropTypes.number,
};

export default VideoPlayer;
