#!/bin/bash
# deployment.sh

# Color codes for better readability
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

# Function to check if Docker is running
check_docker() {
    if ! docker info >/dev/null 2>&1; then
        echo -e "${RED}Error: Docker is not running${NC}"
        exit 1
    fi
}

# Function to deploy services
deploy() {
    echo -e "${YELLOW}Starting deployment...${NC}"
    docker-compose up --build -d
    
    echo -e "${YELLOW}Waiting for services to start...${NC}"
    sleep 10
    
    if docker-compose ps | grep -q "Exit"; then
        echo -e "${RED}Some services failed to start. Checking logs...${NC}"
        docker-compose logs
        exit 1
    fi
    
    echo -e "${GREEN}Deployment completed successfully${NC}"
}

# Function to stop services
stop() {
    echo -e "${YELLOW}Stopping services...${NC}"
    docker-compose down
    echo -e "${GREEN}Services stopped${NC}"
}

# Main script logic
case "$1" in
    "deploy")
        check_docker
        deploy
        ;;
    "stop")
        stop
        ;;
    *)
        echo "Usage: $0 {deploy|stop}"
        exit 1
        ;;
esac
