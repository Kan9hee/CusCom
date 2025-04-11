window.adminCheck = async function checkIsAdmin() {
  try {
    const response = await fetch("/CusCom/API/isAdmin", {
      method: "GET",
      headers: {
        'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
      }
    });

    const data = await response.json();
    return data.isAdmin;
  } catch (error) {
    console.error("Admin check error:", error);
    return false;
  }
}