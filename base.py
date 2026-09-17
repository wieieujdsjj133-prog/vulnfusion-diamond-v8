from abc import ABC, abstractmethod
from typing import List
from app.models.schemas import Finding

class ScannerAgent(ABC):
    name: str = "base"
    tool: str = "base"

    @abstractmethod
    async def scan(self, target: str, previous: List[Finding]) -> List[Finding]:
        raise NotImplementedError
